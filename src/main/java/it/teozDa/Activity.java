package it.teozDa;


import java.util.List;

public class Activity {


    public static void main(String[] args) {

        List<User> users;
        List<Expense> expenses;

        Connector conn = new Connector("jdbc:mysql://127.0.0.1:3306/personaldb", "root", "0000");
        UserRepository userRepository = new UserRepository(conn);
        ExpenseRepository expenseRepository = new ExpenseRepository(conn);

        userRepository.deleteAll();

        User user1 = new User("Matteo", "D'Alessandro", conn);
        User user2 = new User("Luisa", "Aquilino", conn);
        User user3 = new User("Pancrazio", "Franziskainer", conn);
        User user4 = new User("John", "Belushi", conn);



//non dovrei mai creare un utente senza poi aggiungerlo nel database (return ID)...
//        user1.setID(userRepository.insertUser(user1)); //il metodo insertUser ritorna l'ID dell'utente inserito, AUTO_INCREMENT_VALUE
//        user2.setID(userRepository.insertUser(user2));

        users = userRepository.getAllUsers();
        System.out.println(users);

        userRepository.getUserByID(1);
//        userRepository.deleteUser();
        users = userRepository.getAllUsers();
        System.out.println(users);
//        userRepository.updateFirstNameUser(2, "Concetta");
//        userRepository.updateLastNameUser(2, "Aquilino");
        userRepository.getUserByID(4);

        Expense exp1 = new Expense(1, "15-01-2020", "Gen/Feb2020", (double) 260);
        Expense exp2 = new Expense(1, "15-01-2020", "Gen/Feb2020", (double) 260);
        Expense exp3 = new Expense(2, "16-01-2020", "Gen/Feb2020", (double) 32);
        Expense exp4 = new Expense(3, "18-01-2020", "Gen/Feb2020", (double) 24);
        Expense exp5 = new Expense(4, "18-01-2020", "Gen/Feb2020", (double) 56);
        Expense exp6 = new Expense(5, "17-01-2020", "Gen/Feb2020", (double) 21);
        Expense exp7 = new Expense(6, "14-01-2020", "ND", (double) 26);

        expenseRepository.deleteAllExps();
        expenseRepository.insertExpense(user1.getID(), exp1);
        expenseRepository.insertExpense(user2.getID(), exp2);
        expenseRepository.insertExpense(user2.getID(), exp3);
        expenseRepository.insertExpense(user1.getID(), exp4);
        expenseRepository.insertExpense(user2.getID(), exp5);
        expenseRepository.insertExpense(user1.getID(), exp6);
        expenseRepository.insertExpense(user1.getID(), exp7);

        expenseRepository.deleteExpense(1, "15-01-2020", "Affitto");

        expenses = expenseRepository.getExpenseList();
        System.out.println(expenses);



    }
}
