package Modele;

/*
 * 
 * Librairies importÃ©es
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Connexion a votre BDD locale ou Ã  distance sur le serveur de l'ECE via le
 * tunnel SSH
 *
 * @author Camille,Rim,Roman
 */
public class Connexion {

      public  boolean h = false;
    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt, stmt1;
    private ResultSet rset;
    private ResultSet rset1;
    private ResultSetMetaData rsetMeta;
    ArrayList<String> liste = new ArrayList<>();
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requÃªtes de sÃ©lection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requÃªtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramÃ¨tres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

        //crÃ©ation d'une connexion JDBC Ã  la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // crÃ©ation d'un ordre SQL (statement)
        stmt = conn.createStatement();
         if (stmt != null)
        {h =  true ;}


    }

    /**
     * Constructeur avec 4 paramÃ¨tres : username et password ECE, login et
     * password de la BDD Ã  distance sur le serveur de l'ECE
     *
     * @param usernameECE
     * @param passwordECE
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String usernameECE, String passwordECE, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE);

        if (ssh.connect()) {
            System.out.println("Connexion reussie");

            // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;

            //crÃ©ation d'une connexion JDBC Ã  la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // crÃ©ation d'un ordre SQL (statement)
            stmt = conn.createStatement();

        }
    }

    /**
     * MÃ©thode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * MÃ©thode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * MÃ©thode qui ajoute la requete de MAJ en parametre dans son ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * MÃ©thode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" Ã  la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     *
     * @param requete
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<String>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            // ajouter un "\n" Ã  la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * MÃ©thode qui execute une requete de MAJ en parametre
     *
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }

    public int IDgenerator(String table) throws SQLException {

        Random rand = new Random();
        int numRandom;
        boolean uniqueIntWasFound = true;
        
        List<Integer> listeID = new ArrayList<>();
        
        String query = "SELECT numero from " + table + " WHERE 1";
        rset = stmt.executeQuery(query);

        while (rset.next()) {
            int numInterdit = Integer.parseInt(rset.getString("numero"));
            listeID.add(numInterdit);
        }
        while (true) {
            uniqueIntWasFound = true;
            numRandom = rand.nextInt(300) + 1;
            
            for (int el : listeID) 
            {
                if (el == numRandom) 
                {
                    uniqueIntWasFound = false;
                }
            }
            if (uniqueIntWasFound) {
                break;
            }
        }
        
        return numRandom;
    }
    /**
     * Methode qui permet de rechercher des malades en fonction de son nom et prénom
     * @param nom
     * @param prenom
     * @return l'ArrayList des patients correspondants
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList RechercheMalade(String nom, String prenom) throws SQLException, ClassNotFoundException {

        String query = "SELECT m.numero, m.nom, m.prenom, m.adresse, m.tel, m.mutuelle, h.no_chambre, h.lit, h.code_service, s.nom, s.batiment, e.nom FROM malade m, hospitalisation h, chambre c, service s, employe e WHERE m.nom = '" + nom + "' AND m.prenom = '" + prenom + "' AND m.numero = h.no_malade AND h.no_chambre = c.no_chambre AND h.code_service = c.code_service AND c.code_service = s.code AND c.surveillant = e.numero";
        rset = stmt.executeQuery(query);

        if (rset.next()) {
            liste.add(rset.getString("m.prenom"));
            liste.add(rset.getString("m.nom"));
            liste.add(rset.getString("m.numero"));
            liste.add(rset.getString("m.adresse"));
            liste.add(rset.getString("m.tel"));
            liste.add(rset.getString("m.mutuelle"));
            liste.add(rset.getString("h.no_chambre"));
            liste.add(rset.getString("h.lit"));
            liste.add(rset.getString("e.nom"));
            liste.add(rset.getString("s.nom"));
            liste.add(rset.getString("s.batiment"));

        } else {
            String query1 = "SELECT m.nom, m.prenom, m.numero, m.adresse, m.tel, m.mutuelle FROM malade m WHERE m.nom = '" + nom + "' AND m.prenom = '" + prenom + "'";
            rset = stmt.executeQuery(query1);

            if (rset.next()) {
//while (rset.next()) {
                liste.add(rset.getString("m.prenom"));
                liste.add(rset.getString("m.nom"));
                liste.add(rset.getString("m.numero"));
                liste.add(rset.getString("m.adresse"));
                liste.add(rset.getString("m.tel"));
                liste.add(rset.getString("m.mutuelle"));
                //}
            } else {
                String info = "Ce malade n'est pas dans nos rÃ©pertoires.";
                liste.add(info);
            }
        }
        return liste;
    }
/**
 * Methode qui recherche des docteurs en fonction de leur nom et prénom
 * @param nom
 * @param prenom
 * @return l'ArrayList des docteurs correspondants
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public ArrayList RechercheDocteur(String nom, String prenom) throws SQLException, ClassNotFoundException {

        String query = "SELECT e.prenom, e.nom, d.specialite, e.numero, e.adresse, e.tel FROM docteur d, employe e WHERE e.nom = '" + nom + "' AND e.prenom = '" + prenom + "' AND d.numero = e.numero";
        rset = stmt.executeQuery(query);

        if (rset.next()) {
            liste.add(rset.getString("e.prenom"));
            liste.add(rset.getString("e.nom"));
            liste.add(rset.getString("e.numero"));
            liste.add(rset.getString("e.adresse"));
            liste.add(rset.getString("e.tel"));
            liste.add(rset.getString("d.specialite"));
        } else {
            String info = "Cette personne n'est pas dans le repertoire des docteurs.";
            liste.add(info);
        }

        return liste;
    }
/**
 * Methode qui recherche des infirmiers en fonction de leur nom et prénom
 * @param nom
 * @param prenom
 * @return l'ArrayList des docteurs correspondants 
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public ArrayList RechercheInfirmier(String nom, String prenom) throws SQLException, ClassNotFoundException {

        String query = "SELECT e.prenom, e.nom, e.numero, e.adresse, e.tel, i.rotation FROM infirmier i, employe e WHERE e.nom = '" + nom + "' AND e.prenom = '" + prenom + "' AND i.numero = e.numero";
        rset = stmt.executeQuery(query);

        if (rset.next()) {
            liste.add(rset.getString("e.prenom"));
            liste.add(rset.getString("e.nom"));
            liste.add(rset.getString("i.rotation"));
            liste.add(rset.getString("e.numero"));
            liste.add(rset.getString("e.adresse"));
            liste.add(rset.getString("e.tel"));
        } else {
            String info = "Cette personne n'est pas dans le repertoire des infirmiers.";
            liste.add(info);
        }

        return liste;
    }
/**
 * Methode qui recherche les personnes qui sont d'un service en particulier 
 * @param nomService
 * @return l'ArrayList des personnes correspondantes
 * @throws SQLException
 * @throws ClassNotFoundException 
 */ 
    public ArrayList RechercheService(String nomService) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM service s, employe e WHERE s.nom = '" + nomService + "' AND s.directeur = e.numero";
        rset = stmt.executeQuery(query);

        if (rset.next()) {
            //System.out.println("Le service de " + rset.getString("s.nom") + " se situe dans le batiment " + rset.getString("s.batiment") + " et est sous la direction du Dr. " + rset.getString("d.nom") + " " + rset.getString("d.prenom") + ", specialiste " + rset.getString("d.specialite") + ".");
            liste.add(rset.getString("s.nom"));
            liste.add(rset.getString("s.code"));
            liste.add(rset.getString("s.batiment"));
            liste.add(rset.getString("e.prenom"));
            liste.add(rset.getString("e.nom"));
        } else {
            String info = "Ce service n'existe pas.";
            liste.add(info);
        }

        return liste;
    }
    
    
/**
 * Methode qui recherche le bâtiment de la chambre, le surveillant et le nombre de lits
 * Elle prend en paramètre le numéro de chambre et son service
 * @param numChambre
 * @param nomService
 * @return  l'Arraylist correspondant
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public ArrayList RechercheChambre(String numChambre, String nomService) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM chambre c, service s, employe e WHERE c.no_chambre = '" + numChambre + "' AND s.nom = '" + nomService + "' AND c.code_service = s.code AND c.surveillant = e.numero ";
        rset = stmt.executeQuery(query);

        if (rset.next()) {
            //System.out.println("Le service de " + rset.getString("s.nom") + " se situe dans le batiment " + rset.getString("s.batiment") + " et est sous la direction du Dr. " + rset.getString("d.nom") + " " + rset.getString("d.prenom") + ", specialiste " + rset.getString("d.specialite") + ".");
            liste.add(rset.getString("c.no_chambre"));
            liste.add(rset.getString("c.nb_lits"));
            liste.add(rset.getString("c.surveillant"));
            liste.add(rset.getString("e.nom"));
            liste.add(rset.getString("s.nom"));
        } else {
            String info = "Cette chambre dans ce service n'existe pas.";
            liste.add(info);
        }
        return liste;
    }
/**
 * Methode qui compte le nombre de docteurs qui a comme spécialité Cardiologue
 * @return l'entier correspondant
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public int NbdeCardio() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Cardiologue' ";
        rset = stmt.executeQuery(query);
        
        rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
    /**
     * Methode qui compte le nombre de docteurs qui a comme spécilité Traumatologue
     * @return l'entier correspondant
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
     public int NbdeTrauma() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Traumatologue' ";
        rset = stmt.executeQuery(query);
        rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
         /**
     * Methode qui compte le nombre de docteurs qui a comme spécialité Pneumologue
     * @return l'entier correspondant
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
     public int NbdePneu() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Pneumologue' ";
        rset = stmt.executeQuery(query);
       rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
     /**
     * Methode qui compte le nombre de docteurs qui a comme spécialité Orthopediste
     * @return l'entier correspondant
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
      public int NbdeOrth() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Orthopediste' ";
        rset = stmt.executeQuery(query);
        rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
     /**
     * Methode qui compte le nombre de docteurs qui a comme spécialité Radiologuue
     * @return l'entier correspondant
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
      public int NbdeRad() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Radiologue' ";
        rset = stmt.executeQuery(query);
        rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
      /**
     * Methode qui compte le nombre de docteurs qui a comme spécialité Aneshesiste
     * @return l'entier correspondant
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
      public int NbdeAn() throws SQLException, ClassNotFoundException{
        
        String query = "SELECT COUNT(*) FROM docteur WHERE specialite = 'Anesthesiste' ";
        rset = stmt.executeQuery(query);
       rset.next();
        int a;
        a = rset.getInt(1);
       
        return a;
    }
    /**
     * Méthode qui affiche la requête choisie
     * @param num
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void RechercheRandom(String num) throws SQLException, ClassNotFoundException {

        switch (num) {
            case "R0":
                rset = stmt.executeQuery("SELECT * FROM chambre");
                //rsetMeta = rset.getMetaData();

                while (rset.next()) {
                    System.out.println(rset.getString("code_service"));

                }

            case "R1":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT nom, prenom FROM malade WHERE mutuelle = 'MAAF'");
                while (rset.next()) {
                    System.out.println(rset.getString("prenom") + " " + rset.getString("nom"));
                }

            case "R2":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM infirmier i, employe e WHERE i.numero = e.numero AND i.rotation = 'NUIT'");
                while (rset.next()) {
                    System.out.println(rset.getString("prenom") + " " + rset.getString("nom"));
                }

            case "R3":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT s.nom, s.batiment, d.specialite, e.nom, e.prenom FROM service s, docteur d, employe e WHERE s.directeur = d.numero AND d.numero = e.numero");
                while (rset.next()) {
                    System.out.println(rset.getString("s.nom") + " " + rset.getString("s.batiment") + " " + rset.getString("d.specialite") + " " + rset.getString("e.prenom") + " " + rset.getString("e.nom"));
                }

            case "R4":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT c.no_chambre, h.lit, s.nom, m.nom, m.prenom, m.mutuelle FROM chambre c, malade m, hospitalisation h, service s WHERE c.no_chambre = h.no_chambre AND c.code_service = s.code AND m.numero = h.no_malade AND m.mutuelle LIKE 'MN%' AND s.batiment = 'B'");
                while (rset.next()) {
                    System.out.println(rset.getString("c.no_chambre") + " " + rset.getString("h.lit") + " " + rset.getString("s.nom") + " " + rset.getString("m.nom") + " " + rset.getString("m.prenom") + " " + rset.getString("m.mutuelle"));
                }

            case "R5":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT AVG(i.salaire), i.code_service FROM infirmier i GROUP BY i.code_service");
                while (rset.next()) {
                    System.out.println(rset.getString("i.code_service") + " " + rset.getString("AVG(i.salaire)"));
                }

            case "R6":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT c.code_service, AVG(c.nb_lits) FROM chambre c, service s WHERE s.code = c.code_service AND s.batiment = 'A' GROUP BY c.code_service");
                while (rset.next()) {
                    System.out.println(rset.getString("c.code_service") + " " + rset.getString("AVG(c.nb_lits)"));
                }

            case "R7":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT m.nom, m.prenom, COUNT(s.no_docteur), COUNT(DISTINCT d.specialite) FROM soigne s, malade m, docteur d WHERE s.no_malade = m.numero AND s.no_docteur = d.numero GROUP BY s.no_malade HAVING COUNT(s.no_docteur) > 3 ORDER BY nom");
                while (rset.next()) {
                    System.out.println(rset.getString("m.nom") + " " + rset.getString("m.prenom") + " " + rset.getString("COUNT(s.no_docteur)") + " " + rset.getString("COUNT(DISTINCT d.specialite)"));
                }

            case "R8":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT s.nom, COUNT(DISTINCT i.numero) / COUNT(DISTINCT h.no_malade) FROM service s, infirmier i, hospitalisation h WHERE s.code = i.code_service AND h.code_service = s.code GROUP BY s.code");
                while (rset.next()) {
                    System.out.println(rset.getString("s.nom") + " " + rset.getString("COUNT(DISTINCT i.numero) / COUNT(DISTINCT h.no_malade)"));
                }

            case "R9":
                System.out.println("-------------------");
                rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) > '1' ORDER BY nom");
                while (rset.next()) {
                    System.out.println("Dr." + rset.getString("e.nom") + " " + rset.getString("e.prenom"));
                }

            case "R10":
                System.out.println("-------------------");
                System.out.println("Les medecins n'ayant aucun patient hospitalise sont les suivants:");
                rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) = '0' ORDER BY nom");
                while (rset.next()) {
                    System.out.println("Dr." + rset.getString("e.nom") + " " + rset.getString("e.prenom"));
                }
        }
    }
    /**
     * Methode qui retourne le numéro d'une personne dans l'Hôpital 
     * Grâce au nom, prénom et quelle catégorie il fait partie (docteur, patient, infirmier) 
     * @param nom
     * @param prenom
     * @param table
     * @return le numéro d'une personne 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String getNumero(String nom, String prenom, String table) throws SQLException, ClassNotFoundException
       {
           
            String query = "SELECT numero FROM " + table + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "'";
            rset = stmt.executeQuery(query) ;
           
            if(rset.next())
                return rset.getString("numero") ;
            else
                return "0" ;
       }
}
