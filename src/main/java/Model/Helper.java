package Model;

import Database.PropertyDB;
import Database.UserDB;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Database.VendorDB;
import com.mysql.cj.Session;
import org.imgscalr.Scalr;

public class Helper {
    public static String page;
    public static String message;
    public static final String ASSETS_IMAGES_PROPERTIES_LARGE = "/assets/images/properties/large/";
    public static final String ASSETS_IMAGES_PROPERTIES_THUMBS = "/assets/images/properties/thumbs/";
    public static final String IMAGE_EXTENSION = ".jpg";

    public static void ActivateUser(String userID){
        try {
            if (userID != null){
                UserDB userDB = new UserDB();
                Optional<User> user = userDB.getByID(Integer.parseInt(userID));
                user.get().setActive(1);
                userDB.update(user.get());
                page = "GetAllSuspendedUsersServlet";
            }
            else{
                page = "Error.jsp";
                message = "Something went wrong, unable to activate the user";
            }
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
    }
    public static void SuspendUser(String userID){

        try {
            if (userID != null){
                UserDB userDB = new UserDB();
                Optional<User> user = userDB.getByID(Integer.parseInt(userID));
                user.get().setActive(0);
                userDB.update(user.get());
                page = "GetAllActivatedUsersServlet";
            }
            else{
                page = "Error.jsp";
                message = "Something went wrong, unable to suspend the user";
            }
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
    }
    public static ArrayList<User> GetActivatedUsers(){
        ArrayList<User> activatedUsers = new ArrayList<>();
        try{
            List<User> users = new UserDB().getAll();
            for(User u : users){
                if (u.getActive() == 1)
                    activatedUsers.add(u);
            }
            page = "ActivatedUsers.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return activatedUsers;
    }
    public static ArrayList<User> GetSuspendedUsers(){
        ArrayList<User> suspendedUsers = new ArrayList<>();
        try{
            List<User> users = new UserDB().getAll();
            for(User u : users){
                if (u.getActive() == 0)
                    suspendedUsers.add(u);
            }
            page = "SuspendedUsers.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return suspendedUsers;
    }
    public static ArrayList<Property> GetArchivedProperties(int agentID){
        ArrayList<Property> properties = new ArrayList<>();
        try {
            List<Property> propertyList = new PropertyDB().getAll();
            for(Property p : propertyList){
                if (p.getArchived().equals(1) && p.getAgentId().equals(agentID))
                    properties.add(p);
            }
            page = "ArchivedProperties.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return properties;
    }
    public static ArrayList<Property> GetUnarchivedProperties(){
        ArrayList<Property> properties = new ArrayList<>();
        try {
            List<Property> propertyList = new PropertyDB().getAll();
            for(Property p : propertyList){
                if (p.getArchived().equals(0))
                    properties.add(p);
            }
            page = "Properties.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return properties;
    }
    public static ArrayList<Property> GetAgentProperties(int agentID){
        ArrayList<Property> properties = new ArrayList<>();
        try {
            List<Property> propertyList = new PropertyDB().getAll();
            for(Property p : propertyList){
                if (p.getAgentId().equals(agentID) && p.getArchived().equals(0))
                    properties.add(p);
            }
            page = "Properties.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return properties;
    }
    public static Propertynotes getPropertyNote(List<Propertynotes> propertynotes, int propertyID, User user){
            for(Propertynotes pn : propertynotes){
                if (pn.getPropertyId().equals(propertyID) && pn.getUserId().equals(user.getId())){
                    return pn;
                }
            }
        return null;
    }
    public static List<Vendor> GetAllVendors(){

        List<Vendor> vendorsList = new ArrayList<>();
        try{
            vendorsList = new VendorDB().getAll();
            page = "Vendors.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        return vendorsList;
    }
    ///////////IMAGE PROCESS///////////

    public static List<String> getFileNames(ServletContext servletContext, Property property) {
        final String path = servletContext.getRealPath(ASSETS_IMAGES_PROPERTIES_LARGE + property.getListingNum());
        ArrayList<String> imagesName = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if(folder.exists() && listOfFiles != null)
        {
            for (int i = 0; i < listOfFiles.length; i++)
            {
                if (listOfFiles[i].isFile()) {
                    imagesName.add(listOfFiles[i].getName());
                }
            }
        }
        return imagesName;
    }

    public static List<Part> getRequestPartByName(HttpServletRequest request, String name) throws ServletException, IOException {
        return request.getParts().stream()
                .filter(part -> name.equals(part.getName()))
                .collect(Collectors.toList());
    }

    public static void saveImages(ServletContext servletContext, Property property, List<Part> imageParts) throws IOException {
        final String path = servletContext.getRealPath(ASSETS_IMAGES_PROPERTIES_LARGE + property.getListingNum());
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdirs();

        Integer number = 0;
        for (Part imagePart : imageParts) {
            String filePath = getNewImageName(folder + File.separator, property.getListingNum() + "", number);

            Files.copy(imagePart.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);            //imagePart.write(Paths.get(filePath));
            System.out.println(Paths.get(filePath));
        }
    }

    private static String getNewImageName(final String folder, final String baseFilename, Integer number) {
        // get extension Paths.get(imagePart.getSubmittedFileName());

        String filePath = folder + File.separator + baseFilename + (number == 0 ? "" : "-" + number) + IMAGE_EXTENSION;
        File file = new File(filePath);
        while (file.exists()) {
            number++;
            filePath = folder + File.separator + baseFilename + "-" + number + IMAGE_EXTENSION;
            file = new File(filePath);
        }
        return filePath;
    }

    public static BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }

    public static void makeThumbnail(String filepath, String thumbnailFilepath) throws Exception {
        BufferedImage originalImage = ImageIO.read(new File(filepath));
        BufferedImage outputImage = resizeImage(originalImage, 75, 50);
        ImageIO.write(outputImage, "jpg", new File(thumbnailFilepath));
    }

    public static void deleteFolder(File directory) {
        File[] fileSystemObjects = directory.listFiles();
        if (fileSystemObjects != null) {
            for (File fileSystemObject : fileSystemObjects) {
                if (fileSystemObject.isDirectory())
                    deleteFolder(fileSystemObject);
                else
                    fileSystemObject.delete();
            }
        }
        directory.delete();
    }

    public static void deleteImage(String imageFilename, Property property, ServletContext servletContext) {
        final String path = servletContext.getRealPath(ASSETS_IMAGES_PROPERTIES_LARGE + property.getListingNum() + "/" + imageFilename);

        File file = new File(path);
        file.delete();
    }

    public static void setThumbnail(String imageName, Property property, ServletContext servletContext) throws Exception {
        final String path = servletContext.getRealPath(ASSETS_IMAGES_PROPERTIES_LARGE + property.getListingNum() + "/" + imageName);
        final String thumbnailPath = servletContext.getRealPath(ASSETS_IMAGES_PROPERTIES_THUMBS) + property.getListingNum() + IMAGE_EXTENSION;

        System.out.println(path);

        System.out.println(thumbnailPath);

        makeThumbnail(path, thumbnailPath);
    }
}
