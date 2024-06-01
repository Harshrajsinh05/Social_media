 import java.util.*;
import java.sql.*;

class UserProfile {
    String username;
    long follower;
    long likes;
    String varify;

    public UserProfile(String username, long follower, long likes,String varify) {
        this.username = username;
        this.follower = follower;
        this.likes = likes;
        this.varify=varify;
    }

    public String getUsername() {
        return username;
    }

    public long getFollowers() {
        return follower;
    }

    public long getLikes() {
        return likes;
    }

    public void incrementLikes() {
        likes++;
    }

    public void addFollower() {
        follower++;
    }

    public String getVarify()
    {
        return varify;
    }
}

public class App{
    HashMap<String, UserProfile> profiles = new HashMap<>();
    Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        
       


        Scanner sc=new Scanner(System.in);
        
        while (true) {
            int choice=0;
            System.out.println("1. Log in\n2. Exit");
            System.out.print("Enter choice : ");
            try{
            choice = sc.nextInt();
            sc.nextLine(); 
            }
            catch(Exception e)
            {
                System.out.println();
                System.out.println("Enter valid integer number");
                sc.nextLine();
            }
            App I=new App();
            if(choice==1)
            {
                   I.LogIn();
            }
            else if(choice==2)
            {
                System.exit(0);
            }
            else{
                System.out.println("Enter valid choice");
            }
        }
    }


    void LogIn()throws Exception
    {
        String f_username="";
        String f_pass="";
        long f_follower=0;
        long f_likes=0;
        String f_varify="not";
        boolean flag=false;
        int count=0;
        App A=new App();
        //String s_username="";
        System.out.print("Enter a username: ");
        String username = sc.nextLine();
    
            String driverName = "com.mysql.cj.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost:3306/HarshDB";
            String dbuser = "root";
            String dbpass = "";
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement st2=con.createStatement();
            String sql2="select * from social_media";
            ResultSet rs2=st2.executeQuery(sql2);
            while(rs2.next())
                {
                    f_follower=rs2.getLong(5);
                    f_likes=rs2.getLong(6);
                    f_username=rs2.getString(3);
                    f_pass=rs2.getString(4); 
                    f_varify=rs2.getString(7);
                    
                    if(f_username.equals(username))
                    {
                        System.out.println("Username already exists.");
                        System.out.println();
                        
                        do{
                        System.out.println("Enter your password");
                        String pass=sc.nextLine();
                        if(pass.equals(f_pass))
                        {
                            System.out.println();
                            System.out.println("Successfull log in ");
                            flag=true;
                            UserProfile newUser = new UserProfile(username,f_follower,f_likes,f_varify);
                            profiles.put(username, newUser);
                            System.out.println();
                            A.showdetails(username);
                            break;
                        }
                        else{
                            System.out.println("Invalid password");
                            System.out.println();
                            count++;
                        }
                    }while(count!=3);
                    }
                }

                    
                
                 if(flag==false)
                {
                    System.out.println();
                    System.out.println("Invalid username or password");
                    System.out.println();
                    System.out.println("Generate new profile");
                    System.out.println();
                    System.out.println("Enter your firstname");
                    String FirstName=sc.nextLine();
                    System.out.println("Enter Lastname");
                    String LastName=sc.nextLine();
                    System.out.println("Enter new username");
                    String n_username=sc.nextLine();
                    System.out.println("Create new password");
                    String n_pass=sc.nextLine();
                    Statement st=con.createStatement();
                    String sql="insert into social_media(firstname,lastname,username,password,follower,likes,varify) values ('"+FirstName+"','"+LastName+"','"+n_username+"','"+n_pass+"','0','0','not')";
                    int r=st.executeUpdate(sql);
                    if(r>0)
                    {
                        System.out.println();
                        System.out.println("insert successfully");
                        System.out.println();
                    }
                    else{
                        System.out.println("not insert");
                    }
                    UserProfile newUser = new UserProfile(username,f_follower,f_likes,f_varify);
                    profiles.put(username, newUser);
                    System.out.println("Profile for " + n_username + " added.");
                    System.out.println();
                    A.showdetails(n_username);
            }
        }






        void showdetails(String username)throws Exception
        {
            Scanner sc=new Scanner(System.in);
            App A1=new App();
            
         while(true){
            int choice=0;
            System.out.println("1. View Profile\n2. Like Profile\n3. Follow User\n4. Varify your Profile\n.5 Log out");
            try{
            choice = sc.nextInt();
            sc.nextLine(); 
            }
            catch(InputMismatchException e)
            {
                System.out.println();
                System.out.println("Enter valid Integer number");
                sc.nextLine();
            }
            switch (choice) {
                case 1:
                   // System.out.print("Enter username: ");
                    //String username1 = sc.nextLine();
                    A1.viewProfile(username);
                    System.out.println();
                    break;
                case 2:
                    //System.out.print("Enter your name: ");
                    //String likerName = sc.nextLine();
                    System.out.print("Enter username to like: ");
                    String usernameToLike = sc.nextLine();
                    A1.likeProfile(username, usernameToLike);
                    System.out.println();
                    break;
                case 3:
                    //System.out.print("Enter your name: ");
                    //String followerName = sc.nextLine();
                    System.out.print("Enter username to follow: ");
                    String usernameToFollow = sc.nextLine();
                    A1.followUser(username, usernameToFollow);
                    System.out.println();
                    break;
                case 4:
                    A1.varify(username);
                    System.out.println();
                    break;
                case 5:
                    //log out
                    System.out.println("Log out...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            if(choice==5)
        {
            break;
        }
        }
        }
        
        void viewProfile(String username)throws Exception {
            String s_username1="";
             String driverName = "com.mysql.cj.jdbc.Driver";
                String dburl = "jdbc:mysql://localhost:3306/HarshDB";
                String dbuser = "root";
                String dbpass = "";
                Class.forName(driverName);
                Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
                Statement st2=con.createStatement();
                System.out.println();
                int v=0;
                System.out.println("1. for view your profile\n2. for view other profile");
                try{
                v=sc.nextInt();
                sc.nextLine();
                }
                catch(InputMismatchException e)
                {
                    System.out.println();
                    System.out.println("Enter valid integer number");
                    sc.nextLine();
                }
                if(v==1)
                {
                String sql2="select * from social_media where Username='"+username+"'";
                ResultSet rs2=st2.executeQuery(sql2);
                if(rs2.next())
                    {   
                        long s_follower=rs2.getLong(5);
                        long s_likes=rs2.getLong(6);
                        String s_varify=rs2.getString(7);
    
                        UserProfile newUser = new UserProfile(username,s_follower,s_likes,s_varify);
                        profiles.put(username, newUser);
                        UserProfile profile = profiles.get(username);
                        if (profile != null) {
                            if(s_varify.equals("yes"))
                            {
                            profile.likes = s_likes;
                            System.out.println("|-----------------------------------------------");
                            System.out.println("|                                               ");
                            System.out.println("|  useername :"+profile.getUsername()+" | ¥ |                    ");
                            System.out.println("|                                               ");
                            System.out.println("|     Followers              Likes              ");
                            System.out.println("|       "+profile.getFollowers()+"                     "+profile.getLikes()+"              ");
                            System.out.println("|                                               ");
                            System.out.println("|-----------------------------------------------");
                            }
                            else{
                                profile.likes = s_likes;
                                System.out.println("|-----------------------------------------------");
                                System.out.println("|                                               ");
                                System.out.println("|  useername :"+profile.getUsername()+"                      ");
                                System.out.println("|                                               ");
                                System.out.println("|     Followers              Likes              ");
                                System.out.println("|       "+profile.getFollowers()+"                     "+profile.getLikes()+"              ");
                                System.out.println("|                                               ");
                                System.out.println("|-----------------------------------------------");
                            }
                            //System.out.println("Username: " + profile.getUsername());
                            //System.out.println("Followers: " + profile.getFollowers());
                            //System.out.println("Likes: " + profile.getLikes());
                        }
                        else{
                            System.out.println(" Profile not found");
                            System.out.println();
                        }
                        
                    } 
                    else{
                            System.out.println("Profile not found.");
                            System.out.println();
                    }
                }
                else if(v==2)
                {
                    System.out.println("enter username which you want to view profile");
                    String v_username=sc.nextLine();
                    String sql3="select * from social_media where Username='"+v_username+"'";
                ResultSet rs3=st2.executeQuery(sql3);
                if(rs3.next())
                    {   
                        long s_follower1=rs3.getLong(5);
                        long s_likes1=rs3.getLong(6);
                        String s_varify1=rs3.getString(7);
    
                        UserProfile newUser = new UserProfile(v_username,s_follower1,s_likes1,s_varify1);
                        profiles.put(v_username, newUser);
                        UserProfile profile = profiles.get(v_username);
                        if (profile != null) {
                            if(s_varify1.equals("yes"))
                            {
                            profile.likes = s_likes1;
                            System.out.println("|-----------------------------------------------");
                            System.out.println("|                                               ");
                            System.out.println("|  useername :"+profile.getUsername()+" | ¥ |                    ");
                            System.out.println("|                                               ");
                            System.out.println("|     Followers              Likes              ");
                            System.out.println("|       "+profile.getFollowers()+"                     "+profile.getLikes()+"              ");
                            System.out.println("|                                               ");
                            System.out.println("|-----------------------------------------------");
                            }
                            else{
                                profile.likes = s_likes1;
                                System.out.println("|-----------------------------------------------");
                                System.out.println("|                                               ");
                                System.out.println("|  useername :"+profile.getUsername()+"                      ");
                                System.out.println("|                                               ");
                                System.out.println("|     Followers              Likes              ");
                                System.out.println("|       "+profile.getFollowers()+"                     "+profile.getLikes()+"              ");
                                System.out.println("|                                               ");
                                System.out.println("|-----------------------------------------------");
                            }
                            //System.out.println("Username: " + profile.getUsername());
                            //System.out.println("Followers: " + profile.getFollowers());
                            //System.out.println("Likes: " + profile.getLikes());
                        }
                        else{
                            System.out.println(" Profile not found");
                            System.out.println();
                        }
                        
                    } 
                    else{
                            System.out.println("Profile not found.");
                            System.out.println();
                    }
                }
                else
                {
                    System.out.println("Enter valid choice");
                }
            
        }
        void likeProfile(String likerName, String username)throws Exception {
            String s_username="";
            String driverName = "com.mysql.cj.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost:3306/HarshDB";
            String dbuser = "root";
            String dbpass = "";
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement st2=con.createStatement();
            String sql2="select * from social_media where Username='"+username+"'";
            ResultSet rs2=st2.executeQuery(sql2);
            if(rs2.next())
                {
                    long s_follower=rs2.getLong(5);
                    long s_likes=rs2.getLong(6);
                    String s_varify=rs2.getString(7);
                    UserProfile newUser = new UserProfile(username,s_follower,s_likes,s_varify);
                    profiles.put(username, newUser);
                    UserProfile profile = profiles.get(username);
                    if (profile != null)
                    { 
                        profile.incrementLikes();
                        Statement st3=con.createStatement();
                        String sql3="update social_media set likes=(likes+1) where Username='"+username+"'";
                        int r=st3.executeUpdate(sql3);
                        System.out.println(likerName + " liked " + profile.getUsername() + "'s profile!");
                    } 
                    else {
                        System.out.println("Profile not found.");
                        System.out.println();
                    }
                }
            else{
                System.out.println("Profile not found");
                System.out.println();
            }
    }
    void followUser(String followerName, String username)throws Exception {
        String s_username="";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/HarshDB";
        String dbuser = "root";
        String dbpass = "";
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        Statement st2=con.createStatement();
        String sql2="select * from social_media where Username='"+username+"'";
        ResultSet rs2=st2.executeQuery(sql2);
        if(rs2.next())
            {
                long s_follower=rs2.getLong(5);
                long s_likes=rs2.getLong(6);
                String s_varify=rs2.getString(7);
                UserProfile newUser = new UserProfile(username,s_follower,s_likes,s_varify);
                profiles.put(username, newUser);
                UserProfile profile = profiles.get(username);
                if (profile != null)
                {
                    profile.addFollower();
                    Statement st3=con.createStatement();
                    String sql3="update social_media set follower=(follower+1) where Username='"+username+"'";
                    int r=st3.executeUpdate(sql3);
                    System.out.println(followerName + " is now following " + profile.getUsername());
                } else {
                    System.out.println("User or profile not found.");
                    System.out.println();
                }
            }
            else{
                System.out.println("not found");
                System.out.println();
            }
    }
    void varify(String username)throws Exception
    {
        String f_username="";
        String f_pass="";
        long f_follower=0;
        long f_likes=0;
        boolean flag=false;
        //System.out.print("Enter a username: ");
        //String username = sc.nextLine();
    
            String driverName = "com.mysql.cj.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost:3306/HarshDB";
            String dbuser = "root";
            String dbpass = "";
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement st2=con.createStatement();
            String sql2="select * from social_media";
            ResultSet rs2=st2.executeQuery(sql2);
            while(rs2.next())
                {
                    flag=true;
                    f_follower=rs2.getLong(5);
                    f_likes=rs2.getLong(6);
                    f_username=rs2.getString(3);
                    f_pass=rs2.getString(4); 
                    
                    if(f_username.equals(username))
                    {
                        if(f_likes>=500&&f_follower>=500)
                        {
                            Statement st=con.createStatement();
                            String sql="update social_media set varify='yes' where Username='"+username+"'";
                            int r=st.executeUpdate(sql);
                            if(r>0)
                            {
                                System.out.println("your profile now been varifid");
                            }
                        }
                        else{
                            System.out.println("Your request is denied");
                        }
                    }
                }
                if(!flag)
                {
                    System.out.println("Enter valid username");
                }
            
    }
}