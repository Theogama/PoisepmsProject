import java.sql.*;
import java.util.Scanner;
/**
 * @author Sandile
 *
 */
public class poisepms {
	
	/**
	 * @param args
	 * 
	 * throws SQLException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		
		// Connect to the poisepms database, via the jdbc:mysql: channel on localhost (this PC)
		// Use user name "root", password "@Theo1996".
		
		while(true) {
	
		try {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poisepms?useSSL=false",
				"root",
				"@Theo1996"
				);
		
		Statement statement = connection.createStatement();
		ResultSet results;
		int rowsAffected;
		
		results = statement.executeQuery("SELECT Project_name, Deadline FROM project_management");
		
		// Loop over the results, printing them all.
        while (results.next()) {
            System.out.println(results.getString("Project_name") + ", " +results.getString("Deadline"));
            
        }
        
    
        Scanner scan = new Scanner(System.in);
		System.out.println(scan);
		
		// Created a menu for the user.
	
		System.out.println("M E N U");
		System.out.println("=======");
		System.out.println("1. Capture New project.");
		System.out.println("2. Update infornation about existing projects.");
		System.out.println("3. Finalising The Project.");
		System.out.println("4. See a list of projects that still need to be completed.");
		System.out.println("5. See a list of projects that are past the due date.");
		System.out.println("6. exit Task mangement.");
		System.out.println("=======");
        
		System.out.print("Enter your choice:\n");
		int choice = scan.nextInt();
	
		if(choice == 1) {
			while(true) {
			System.out.print("Enter Project number:\n");
			int Project_ID = scan.nextInt();
			scan = new Scanner(System.in);
			System.out.print("Enter Project name:\n");
			String Project_name = scan.nextLine();
			System.out.print("What type of building is being designed?:\n");
			String built_type = scan.nextLine();
			
			System.out.print("Enter The physical address for the project:\n");
			String address = scan.nextLine();
			
			System.out.print("Enter ERF number:\n");
			int EFR = scan.nextInt();
			
			System.out.print("Enter The total fee being charged for the project:\n");
			double total_fee = scan.nextDouble();
			
			System.out.print("Enter The total amount paid to date:\n");
			double amount_deposited = scan.nextDouble();
			
			scan = new Scanner(System.in);
			System.out.print("What is the deadline of the project >>>> Use this format 'yyyy-mm-dd':\n");
			String Deadline = scan.nextLine();
			
			System.out.print("Enter Customer email address:\n");
			String customer_Email = scan.nextLine();

			System.out.print("Enter Customer contact details:\n");
			int Customer_phone_number = scan.nextInt();
			
			scan = new Scanner(System.in);
			System.out.print("Enter Address of Customer:\n");
			String customer_Address = scan.nextLine();
			
			System.out.print("Enter Name of an Customer:\n");
			String customer_name = scan.nextLine();
			
			
			// Inserts the new book to the database.
			rowsAffected = statement.executeUpdate("INSERT INTO project_management VALUES (" + Project_ID + "," + "'"+Project_name+"'" + "," + "'"+built_type+"'" + "," + 
			"'"+address+"'" + "," + EFR + "," + total_fee + "," + amount_deposited + "," + "'"+Deadline+"'" + ")");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			PrintAllFromTable(statement);
			
			rowsAffected = statement.executeUpdate(
                    "INSERT INTO project_information VALUES ('Contractor', 'SJ Smith', 0113659987, 'SJSmith@gmail.com', '23 Craig avenue')"
                );
            System.out.println("Query complete, " + rowsAffected + " rows added.");
            PrintAllFromTable(statement);
            
            
            rowsAffected = statement.executeUpdate(
                    "INSERT INTO project_information VALUES ('Customer'," + "'"+customer_name+"'" + "," + Customer_phone_number + "," + "'"+customer_Email+"'" + "," + "'"+customer_Address+"'" + ")"
                );
            System.out.println("Query complete, " + rowsAffected + " rows added.");
            PrintAllFromTable(statement);
            
            rowsAffected = statement.executeUpdate(
                    "INSERT INTO project_information VALUES ('Architect', 'NK Khoza', 0110975409, 'NKKhoza@gmail.com', '6 Walkden avenue')"
                );
            System.out.println("Query complete, " + rowsAffected + " rows added.");
            PrintAllFromTable(statement);
            
            System.out.println("Return to The menu >>> Enter '00':");
            int returnMenu = scan.nextInt();
            if(returnMenu == 00) {
            	break;
            }
		}
		
		}
		else if(choice == 2) {
			
			
			System.out.println("Enter Project ID where you want to update:");
			int update = scan.nextInt();
			
			//Edit menu is displayed to the user.
			System.out.println("=======");

			System.out.print("To edit Project_name '01'\n" + 
							"To edit Built type '02'\n" +
							"To edit Building address '03'\n" +
							"To edit EFR '04'\n" +
							"To edit total_fee '05'\n" + 
							"To edit amoount deposited '06'\n" +
							"To edit Deadline '07'\n"
					);
			
			System.out.println("=======");   

			
			System.out.println("Enter where you want to update in the database:");
			int To_Update = scan.nextInt();
			
			// if a user edits the Project name.
			if(To_Update == 01) {
				
				
				scan = new Scanner(System.in);
				System.out.println("Enter new Project name:");
				String name = scan.nextLine();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Project_name=" + "'"+name+"'" + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
			//If the user edits building type.
			if(To_Update == 02) {
				
				scan = new Scanner(System.in);
				System.out.println("Enter new Building type:");
				String type = scan.nextLine();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Built_type=" + "'"+type+"'" + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
			//if the user edits address.
			if(To_Update == 03) {
				
				scan = new Scanner(System.in);
				System.out.println("Enter new Address:");
				String address = scan.nextLine();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Address=" + "'"+address+"'" + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
			// if the user edits EFR.
			if(To_Update == 04) {
				  
				
				System.out.println("Enter new EFR:");
				int EFR = scan.nextInt();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET EFR=" + EFR + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			// If the user edits Total balance. 
			if(To_Update == 05) {
				
				System.out.println("Enter new Total amount:");
				double amount = scan.nextDouble();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Total_balance=" + amount + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
			// if the user edits deposited amount.
			if(To_Update == 06) {
					
				System.out.println("Enter new deposited amount:");
				double deposited = scan.nextDouble();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Amount_Paid=" + deposited + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
			// if the user edits deadline of the project.
			if(To_Update == 07) {
				
				scan = new Scanner(System.in);
				System.out.println("Enter new Deadline:");
				String Deadline = scan.nextLine();
				rowsAffected = statement.executeUpdate(
				"UPDATE project_management SET Deadline=" + "'"+Deadline+"'" + "WHERE Project_ID="+ update );
				System.out.println("Query complete, " + rowsAffected + " rows updated.");
				PrintAllFromTable(statement);
			}
			
		}
			
		
		else if(choice == 3) {
			while(true) {
			System.out.println("Enter Project ID where you want to Finalize project:");
			int finalise = scan.nextInt();
			
			scan = new Scanner(System.in);
			System.out.print("Enter Name of an Customer:\n");
			String customer_name = scan.nextLine();
			
			System.out.println("Enter new deposited amount:");
			double deposited = scan.nextDouble();
			rowsAffected = statement.executeUpdate(
			"UPDATE project_management SET Amount_Paid=" + deposited + "WHERE Project_ID="+ finalise );
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			
			System.out.println("Enter new Total amount:");
			double amount = scan.nextDouble();
			rowsAffected = statement.executeUpdate(
			"UPDATE project_management SET Total_balance=" + amount + "WHERE Project_ID="+ finalise );
			System.out.println("Query complete, " + rowsAffected + " rows updated.");
			
			if(deposited == amount) {
				System.out.println("INVOICE FOR PROJECT " + finalise );
				System.out.println("===========================");
				System.out.println("Customer name:" + customer_name );
				System.out.println("Deposited amount: R" + deposited);
				System.out.println("Total amount: R" + amount);
				
				System.out.println("DATE:_______________");
				System.out.println("SIGNATURE:_______________");
				
			}else {
				System.out.println("The payment is not Complete!!!");
			}
			System.out.println("Return to The menu >>> Enter '00':");
            int returnMenu = scan.nextInt();
            if(returnMenu == 00) {
            	break;
            }
		}
		}
		else if(choice == 4) {
			
			
			while(true) {
			results = statement.executeQuery("SELECT Project_name, Amount_Paid, Total_balance FROM project_management WHERE Amount_Paid != Total_balance");
			
			// Loop over the results, printing them all.
	        while (results.next()) {
	            System.out.println(results.getString("Project_name") + ", " +results.getDouble("Amount_Paid") + ", " +results.getDouble("Total_balance"));
	            
	        }
	        
	        System.out.println("Return to The menu >>> Enter '00':");
            int returnMenu = scan.nextInt();
            if(returnMenu == 00) {
            	break;
            }
			}
		}
		else if(choice == 5) {
			while(true) {
			 // executeQuery: runs a SELECT statement and returns the results.
            results = statement.executeQuery("SELECT * FROM project_management WHERE curdate() >= Deadline;");
            // Loop over the results, printing them all.
            while (results.next()) {
                System.out.println(results.getString("Project_name") + ", " +results.getString("Deadline"));
                
            }
            System.out.println("Return to The menu >>> Enter '00':");
            int returnMenu = scan.nextInt();
            if(returnMenu == 00) {
            	break;
            }
            }
			
		}
		else if(choice == 6) {
			
			System.out.println("Thank you for using our System.....Have a great day!!!!");
			break;	
		}
		
		// Close up our connections
        results.close();
        statement.close();
        connection.close();
		
		}catch(Exception e) {
			System.out.println("Something went wrong while compiling!!!!");
		}
		}
	}
	

	/**
     * Method printing all values in all rows.
     * Takes a statement to try to avoid spreading DB access too far.
     * @param statement on an existing connection
     * throws SQLException
     */
	public static void PrintAllFromTable(Statement statement) {
		
		try {
			ResultSet results = statement.executeQuery("SELECT Project_ID, Project_name , Build_type, ERF, Total_balance, Amount_Paid, Deadline FROM project_management");
	        while (results.next()) {
	            System.out.println(
	                    results.getInt("Project_ID") + ", "
	                    + results.getString("Project_name") + ", "
	                    + results.getString("Build_type") + ", "        
	                    + results.getInt("ERF") + ", "
	                    + results.getBigDecimal("Total_balance") + ", "
	                    + results.getBigDecimal("Amount_Paid") + ", "
	                    + results.getString("Deadline")
	               );
	            
	        }
        }catch(SQLException e) {
        	System.out.println("Something went wrong!!!!!");
        }
    }
	
}
