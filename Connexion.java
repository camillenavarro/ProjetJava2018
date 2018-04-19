package Modele;

/*
 * 
 * Librairies importées
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author segado
 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
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

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
        
        //R0
        rset = stmt.executeQuery("SELECT * FROM chambre");
        //rsetMeta = rset.getMetaData();
        
        while(rset.next()){
            System.out.println(rset.getString("code_service"));
            
        }
        
        //R1
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT nom, prenom FROM malade WHERE mutuelle = 'MAAF'");
        while(rset.next()){
            System.out.println(rset.getString("prenom") + " " + rset.getString("nom"));        
        }
        
        //R2
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM infirmier i, employe e WHERE i.numero = e.numero AND i.rotation = 'NUIT'");
        while(rset.next()){
            System.out.println(rset.getString("prenom") + " " + rset.getString("nom"));        
        }
        
        //R3
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT s.nom, s.batiment, d.specialite, e.nom, e.prenom FROM service s, docteur d, employe e WHERE s.directeur = d.numero AND d.numero = e.numero");
        while(rset.next()){
            System.out.println(rset.getString("s.nom") + " " + rset.getString("s.batiment")+ " " + rset.getString("d.specialite") + " " + rset.getString("e.prenom") + " " + rset.getString("e.nom"));   
        }
        
        //R4
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT c.no_chambre, h.lit, s.nom, m.nom, m.prenom, m.mutuelle FROM chambre c, malade m, hospitalisation h, service s WHERE c.no_chambre = h.no_chambre AND c.code_service = s.code AND m.numero = h.no_malade AND m.mutuelle LIKE 'MN%' AND s.batiment = 'B'");
        while(rset.next()){
            System.out.println(rset.getString("c.no_chambre") + " " + rset.getString("h.lit") + " " + rset.getString("s.nom") + " " + rset.getString("m.nom") + " " + rset.getString("m.prenom") + " " + rset.getString("m.mutuelle"));        
        }
        
        //R5
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT AVG(i.salaire), i.code_service FROM infirmier i GROUP BY i.code_service");
        while(rset.next()){
            System.out.println(rset.getString("i.code_service") + " " + rset.getString("AVG(i.salaire)"));        
        }
        
        //R6
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT c.code_service, AVG(c.nb_lits) FROM chambre c, service s WHERE s.code = c.code_service AND s.batiment = 'A' GROUP BY c.code_service");
        while(rset.next()){
            System.out.println(rset.getString("c.code_service") + " " + rset.getString("AVG(c.nb_lits)"));        
        }
        
        //R7
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT m.nom, m.prenom, COUNT(s.no_docteur), COUNT(DISTINCT d.specialite) FROM soigne s, malade m, docteur d WHERE s.no_malade = m.numero AND s.no_docteur = d.numero GROUP BY s.no_malade HAVING COUNT(s.no_docteur) > 3 ORDER BY nom");
        while(rset.next()){
            System.out.println(rset.getString("m.nom") + " " + rset.getString("m.prenom") + " " + rset.getString("COUNT(s.no_docteur)") + " " + rset.getString("COUNT(DISTINCT d.specialite)"));        
        }
        
        //R8
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT s.nom, COUNT(DISTINCT i.numero) / COUNT(DISTINCT h.no_malade) FROM service s, infirmier i, hospitalisation h WHERE s.code = i.code_service AND h.code_service = s.code GROUP BY s.code");
        while(rset.next()){
            System.out.println(rset.getString("s.nom") + " " + rset.getString("COUNT(DISTINCT i.numero) / COUNT(DISTINCT h.no_malade)"));
        }
        
        //R9
        System.out.println("-------------------");
        rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) > '1' ORDER BY nom");
        while(rset.next()){
            System.out.println("Dr." + rset.getString("e.nom") + " " + rset.getString("e.prenom"));
        }
        
        //R10
        System.out.println("-------------------");
        System.out.println("Les medecins n'ayant aucun patient hospitalise sont les suivants:");
        rset = stmt.executeQuery("SELECT e.nom, e.prenom FROM employe e, soigne s WHERE e.numero = s.no_docteur GROUP BY s.no_docteur HAVING COUNT(s.no_malade) = '0' ORDER BY nom");
        while(rset.next()){
            System.out.println("Dr." + rset.getString("e.nom") + " " + rset.getString("e.prenom")); }
        
       /*Scanner sc = new Scanner(System.in);
        
       //System.out.println("Veuillez saisir un nom :");
       String nom = sc.nextLine();
       System.out.println("Veuillez saisir un prenom : ");
       String prenom = sc.nextLine();*/
           
        //}
        
       //System.out.println("Veuillez saisir une table : ");
       //String table = sc.nextLine();
       
       //String query = "SELECT * FROM " + table; 
        
        //rset = stmt.executeQuery(query);
        
        //while(rset.next()){
        //    System.out.println(rset.getString("code_service"));  
    }
    //}

    /**
     * Constructeur avec 4 paramètres : username et password ECE, login et
     * password de la BDD à distance sur le serveur de l'ECE
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

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();

        }
    }
    
    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
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

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
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

            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    
    public void RecherchePersonne(String nom, String prenom) throws SQLException, ClassNotFoundException {
        
       String query = "SELECT h.no_chambre, h.lit, h.code_service FROM malade m, hospitalisation h WHERE nom = '" + nom + "' AND prenom = '" + prenom + "' AND m.numero = h.no_malade";
       System.out.println(query);
       rset = stmt.executeQuery(query);
        
       while(rset.next()){
            System.out.println("La chambre du malade hospitalise est " + rset.getString("h.no_chambre") + " dans le sevice " + rset.getString("h.code_service"));   
        
        }
    }
    
}
