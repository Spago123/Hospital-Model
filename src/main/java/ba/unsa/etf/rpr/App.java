package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.bussines.DepartmentManager;
import ba.unsa.etf.rpr.bussines.DiagnosisManager;
import ba.unsa.etf.rpr.bussines.DoctorManager;
import ba.unsa.etf.rpr.bussines.PatientManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;

/**
 * CLI interface for Application that models a hospital
 * @author Harun Špago
 * @version 1.0.0
 */
public class App {
    private static final Option addDepartment = new Option("addD", "addDepartment", true,
            "Adding new department to database");
    private static final Option deleteDepartment = new Option("delD", "deleteDepartment", true,
            "Deleting department from database");

    private static final Option showDepartments = new Option("showD", "showDepartments", false,
            "Show all departments");

    private static final Option addDoctor = new Option("addDoc", "addDoctor", true,
            "Add new doctor to database");
    private static final Option deleteDoctor = new Option("deleteDoc", "deleteDoctor", true,
            "Deleting doctor from database");

    private static final Option showDoctors = new Option("showDoc", "showDoctors", false,
            "Show all doctors");

    private static final Option addPatient = new Option("addP", "addPatient", true,
            "Adding new patient to database");


    private static final Option deletePatient = new Option("delP", "deletePatient", true,
            "Deleting patient from database");

    private static final Option showPatients = new Option("showP", "showPatients", false,
            "Show all patients");

    private static final Option addHistory = new Option("addH", "addHistory", true,
            "Adding new history to database");

    private static final Option deleteHistory = new Option("delH", "deleteHistory", true,
            "Deleting history from database");

    private static final Option showHistories = new Option("showH", "showHistories", false,
            "Show all histories");

    public static void printFormattedOptions(Options options){
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekatRPR-cli-jar-with-dependencies.jar [option] 'something else if needed'");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {

        Options options = new Options();
        options.addOption(addDepartment);
        options.addOption(deleteDepartment);
        options.addOption(showDepartments);
        options.addOption(addDoctor);
        options.addOption(deleteDoctor);
        options.addOption(showDoctors);
        options.addOption(addPatient);
        options.addOption(deletePatient);
        options.addOption(showPatients);
        options.addOption(addHistory);
        options.addOption(deleteHistory);
        options.addOption(showHistories);
        return options;
    }



    public static void main(String[] args ){

            try {
                Options options = addOptions();
                CommandLineParser commandLineParser = new DefaultParser();
                CommandLine commandLine = commandLineParser.parse(options, args);

                //System.out.println(commandLine.getArgList().get(1));
                //System.out.println(args);

                if (commandLine.hasOption(showDepartments.getOpt()) || commandLine.hasOption(showDepartments.getLongOpt())) {
                    System.out.println("Hera iam");
                    DepartmentManager departmentManager = new DepartmentManager();
                    System.out.println(departmentManager.getAll());
                } else if (commandLine.hasOption(addDepartment.getOpt()) || commandLine.hasOption(addDepartment.getLongOpt())) {
                    DepartmentManager departmentManager = new DepartmentManager();
                    departmentManager.add(new Department(1, commandLine.getArgList().get(0)));
                } else if (commandLine.hasOption(deleteDepartment.getOpt()) || commandLine.hasOption(deleteDepartment.getLongOpt())) {
                    DepartmentManager departmentManager = new DepartmentManager();
                    departmentManager.delete(Integer.parseInt(commandLine.getArgList().get(0)));
                } else if (commandLine.hasOption(showDoctors.getOpt()) || commandLine.hasOption(showDoctors.getLongOpt())) {
                    DoctorManager doctorManager = new DoctorManager();
                    System.out.println(doctorManager.getAll());
                } else if (commandLine.hasOption(addDoctor.getOpt()) || commandLine.hasOption(addDoctor.getLongOpt())) {
                    String name , pass, depName;
                    if(commandLine.getArgList().size() == 4){
                        name = commandLine.getArgList().get(0) + " " + commandLine.getArgList().get(1);
                        pass = commandLine.getArgList().get(2);
                        depName = commandLine.getArgList().get(3);
                    } else {
                        name = commandLine.getArgList().get(0);
                        pass = commandLine.getArgList().get(1);
                        depName = commandLine.getArgList().get(2);
                    }
                    DoctorManager doctorManager = new DoctorManager();
                    DepartmentManager departmentManager = new DepartmentManager();
                    List<Department> department = departmentManager.getByName(depName);
                    if (department.size() == 0) {
                        System.out.println("Department with name " + depName + " does not exists");
                    } else if (department.size() != 0) {
                        doctorManager.add(new Doctor(1, name, pass, department.get(0)));
                        System.out.println("You've successfully added a new Doctor");
                    }
                } else if (commandLine.hasOption(deleteDoctor.getOpt()) || commandLine.hasOption(deleteDoctor.getLongOpt())) {
                    DoctorManager doctorManager = new DoctorManager();
                    doctorManager.delete(Integer.parseInt(commandLine.getArgList().get(0)));
                } else if (commandLine.hasOption(showPatients.getOpt()) || commandLine.hasOption(showPatients.getLongOpt())) {
                    PatientManager patientManager = new PatientManager();
                    System.out.println(patientManager.getAll());
                } else if (commandLine.hasOption(addPatient.getOpt()) || commandLine.hasOption(addPatient.getLongOpt())) {
                    String name, pass;
                    Long UIN;
                    int id;
                    if(commandLine.getArgList().size() == 5){
                        name = commandLine.getArgList().get(0) + " " + commandLine.getArgList().get(1);
                        pass = commandLine.getArgList().get(2);
                        UIN = Long.parseLong(commandLine.getArgList().get(3));
                        id = Integer.parseInt(commandLine.getArgList().get(4));
                    } else {
                        name = commandLine.getArgList().get(0) + " " + commandLine.getArgList().get(1);
                        pass = commandLine.getArgList().get(2);
                        UIN = Long.parseLong(commandLine.getArgList().get(3));
                        id = Integer.parseInt(commandLine.getArgList().get(4));
                    }
                    PatientManager patientManager = new PatientManager();
                    DoctorManager doctorManager = new DoctorManager();
                    Doctor doctor = doctorManager.getById(id);
                    if (doctor == null) {
                        System.out.println("Doctor does not exists");
                    } else if (doctor != null) {
                        patientManager.add(new Patient(1, name, pass,
                                UIN, doctor));
                        System.out.println("You've successfully added a new Patient");
                    }
                } else if (commandLine.hasOption(deletePatient.getOpt()) || commandLine.hasOption(deletePatient.getLongOpt())) {
                    System.out.println(Integer.parseInt(commandLine.getArgList().get(0)));
                    PatientManager patientManager = new PatientManager();
                    System.out.println(patientManager.getById(Integer.parseInt(commandLine.getArgList().get(0))));
                    patientManager.delete(Integer.parseInt(commandLine.getArgList().get(0)));
                } else if (commandLine.hasOption(showHistories.getOpt()) || commandLine.hasOption(showHistories.getLongOpt())) {
                    DiagnosisManager diagnosisManager = new DiagnosisManager();
                    System.out.println(diagnosisManager.getAll());
                } else if (commandLine.hasOption(addHistory.getOpt()) || commandLine.hasOption(addHistory.getLongOpt())) {
                    PatientManager patientManager = new PatientManager();
                    DoctorManager doctorManager = new DoctorManager();
                    DiagnosisManager diagnosisManager = new DiagnosisManager();
                    Doctor doctor = doctorManager.getById(Integer.parseInt(commandLine.getArgList().get(1)));
                    Patient patient = patientManager.getById(Integer.parseInt(commandLine.getArgList().get(0)));
                    if (doctor == null || patient == null) {
                        System.out.println("Doctor or patient does not exists");
                    } else if (doctor != null || patient != null) {
                        diagnosisManager.add(new History(1, patient, doctor, commandLine.getArgList().get(2)));
                        System.out.println("You've successfully added a new History");
                    }
                } else if (commandLine.hasOption(deleteHistory.getOpt()) || commandLine.hasOption(deleteHistory.getLongOpt())) {
                    DiagnosisManager diagnosisManager = new DiagnosisManager();
                    diagnosisManager.delete(Integer.parseInt(commandLine.getArgList().get(0)));
                } else {
                    printFormattedOptions(options);
                }
            } catch (ParseException pe) {
                System.out.println("Problems while entering data");
            } catch (Exception pe) {
                System.out.println(pe.getMessage());
            }
        }

}
