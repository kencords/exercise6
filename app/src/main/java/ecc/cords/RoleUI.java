package ecc.cords;

public class RoleUI {

	private static RoleUI roleUI = null;
	private static DaoService daoService = new DaoService();
	private static String logMsg = "";

	public  void manageRoles() {
		while(true) {
			System.out.print("\033\143");
			System.out.println("MANAGE ROLES...");
			System.out.println(logMsg.equals("")? "" : "\n" + logMsg + "\n");
			logMsg = "";
			System.out.println("\nROLES LIST:\n");
			System.out.println(getRoles());
			System.out.println("1. ADD ROLE");
			System.out.println("2. EDIT ROLE");
			System.out.println("3. DELETE ROLE");
			System.out.println("4. BACK");
			Role role = new Role();
			String choice = InputHelper.askChoice("What do you want to do? (Enter Choice Number): ");
			try {
				switch(choice) {
					case "1":
						logMsg = EmployeeManager.createRole(InputHelper.askString("Enter Role Name: ", false).toUpperCase());
						break;
					case "2":
						role = EmployeeManager.getRole(InputHelper.askPositiveNumber("Enter Role ID: ", false));
						logMsg = EmployeeManager.updateRole(role,InputHelper.askString("Enter Role Name: ", false).toUpperCase());
						break;
					case "3":
						role = EmployeeManager.getRole(InputHelper.askPositiveNumber("Enter Role ID: ", false));
						logMsg = EmployeeManager.deleteRole(role);
						break;
					case "4":
						return;
				}
			}catch(Exception exception) {
				logMsg = EmployeeManager.getLogMsg();
				if(choice.equals("2")||choice.equals("3")){
					logMsg += "\nRole is currently used by:" + getRoleOwners(role);
				}
			}
		}
	}

	public String getFilteredRoles(Employee employee) {
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).stream()
											 .filter(role -> !employee.getRoles().contains(role))
											 .sorted((role1,role2) -> Long.compare(role1.getRoleId(), role2.getRoleId()))
											 .forEach(role -> sb.append(role + "\n"));
		return sb.toString();
	}

	public String getRoles() {
		StringBuilder sb = new StringBuilder();
		daoService.getAllElements(Role.class).stream()
											 .sorted((role1,role2) -> Long.compare(role1.getRoleId(), role2.getRoleId()))
											 .forEach(role -> sb.append(role + "\n"));
		return sb.toString();
	}

	private String getRoleOwners(Role role) {
		StringBuilder sb = new StringBuilder();
		role.getEmployees().stream()
						   .sorted((emp1,emp2) -> Long.compare(emp1.getEmpId(), emp2.getEmpId()))
		                   .forEach(employee -> sb.append("\n" + employee));
		return sb.toString();
	}

	public static RoleUI getInstance() {
		if(roleUI == null) {
			roleUI = new RoleUI();
		}
		return roleUI;
	}
}