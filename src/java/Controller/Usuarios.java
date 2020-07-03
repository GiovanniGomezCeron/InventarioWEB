package Controller;

import java.io.IOException;
import Model.Usuario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.UsuarioDAO;
import DAO.UsuarioDAOImp;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Usuarios extends HttpServlet {

    UsuarioDAO user = new UsuarioDAOImp();
    Usuario u = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        try {
            switch (opcion) {
                case "listar":
                    listar(request, response);
                    break;
                case "eliminar":
                    int id = Integer.valueOf(request.getParameter("id"));
                    this.eliminar(request, response, id);
                    break;
                case "comprobar":
                    this.ComprobarUsuario(request, response);
                    break;
                case "salir":
                    this.CerrarSesion(request, response);
                    break;
                case "pregunta":
                    this.ComprobarPregunta(request, response);
                    break;
                case "recuperar":
                    this.Recuperar(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        u.setId(Integer.parseInt(request.getParameter("id")));
        u.setNombre(request.getParameter("nombre"));
        u.setApellido(request.getParameter("apellido"));
        u.setCorreo(request.getParameter("email"));
        u.setClave(this.EncriptMD5(request.getParameter("con")));
        u.setUsuario(request.getParameter("nUsuario"));
        u.setEstado(String.valueOf(1));
        u.setTipo("1");
        String direccion = "";
        if (request.getParameter("opcion").equals("")) {
            u.setPregunta(request.getParameter("pregunta"));
            u.setRespuesta(request.getParameter("respuesta"));
            user.Guardar(u);           
            direccion = "Inicio.jsp";
        } else {
            user.Editar(u);

            direccion = request.getContextPath() + "/Usuarios?opcion=listar";
        }
        response.sendRedirect(direccion);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        UsuarioDAO user = new UsuarioDAOImp();
        HttpSession session = request.getSession(true);
        session.setAttribute("lista", user.Listar());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listarUsuarios.jsp");
        dispatcher.forward(request, response);

    }

    public void eliminar(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        if (user.Eliminar(id)) {
            this.listar(request, response);
        }
    }

    public void ComprobarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        PrintWriter out=response.getWriter();
        String co= this.EncriptMD5(request.getParameter("password"));
        String[] respuesta = user.validarUser(request.getParameter("user"),co);
        String direccion = request.getContextPath();
        if (respuesta[0]!=null) {
            direccion = request.getContextPath() + "/Seguridad/Control.jsp?id=" + respuesta[0] + "&&pregunta=" + respuesta[1];
            HttpSession con=request.getSession(true);
            con.setAttribute("Contraseña",request.getParameter("password"));  
        }
        response.sendRedirect(direccion);

    }

    public void ComprobarPregunta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String respuesta = user.ValidarRespuesta(id, request.getParameter("respuesta"));
        String direccion = request.getContextPath() + "/index.jsp";
        if (respuesta!=null) {
            direccion = request.getContextPath() + "/Inicio.jsp";
            HttpSession session_user = request.getSession(true);
            String user = String.valueOf(id);
            session_user.setAttribute("Usuario", user);
            
        }
        response.sendRedirect(direccion);

    }

    public void CerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("Usuario");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void Recuperar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String email = "";
        email = user.Email(request.getParameter("correo"));
        String direccion = request.getContextPath() + "/Seguridad/Rec.jsp?c=" + email;
        if (email.isEmpty()) {
            direccion = request.getContextPath() + "/index.jsp";
        }
        response.sendRedirect(direccion);

    }

    public String EncriptMD5(String cadena) {
        String hashtext="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(cadena.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
             hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashtext;
    }
//    public String DesEncript(String cadena)throws Exception{
//        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
//        String base64EncryptedString = "";
//
//        try {
//          //  byte[] message = Base64.decodeBase64(cadena.getBytes("utf-8"));
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
//            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//
//            Cipher decipher = Cipher.getInstance("DESede");
//            decipher.init(Cipher.DECRYPT_MODE, key);
//
//            byte[] plainText = decipher.doFinal(message);
//
//            base64EncryptedString = new String(plainText, "UTF-8");
//
//        } catch (Exception ex) {
//        }
//        return base64EncryptedString;
//    }
//}
    }

