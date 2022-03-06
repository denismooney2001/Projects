<?php
require '../model/database.php';
require '../model/employee_db.php';
require '../model/timesheet_db.php';

$action = filter_input(INPUT_POST, 'action');
if($action == NULL ){
    $action = filter_input(INPUT_GET, 'action');
    if($action == NULL){
        $action = 'Select_employee';
    }
}

switch ($action) {
    case 'Select_employee':
        
       $employees = get_employeeDetails();
       include '../view/employeeDetails.php';
       break;
    
    case 'enter_emp_hours':
        
        $id = filter_input(INPUT_POST, 'id');
        if($id == NULL){
            $id = filter_input(INPUT_GET, 'id');
        }
        $date = filter_input(INPUT_POST, 'date');
        if($date == NULL){
            $date = filter_input(INPUT_GET, 'date');
        }    
        $employee_timesheet_info = get_employee_timesheet($id, $date);
        $employee_info = get_selected_employee($id);
        
        $totalSaturday = totalSaturday($id, $date);
        $totalSunday = totalSunday($id, $date);
        $totalMonday = totalMonday($id, $date);
        $totalTuesday = totalTuesday($id, $date);
        $totalWednesday = totalWednesday($id, $date);
        $totalThursday= totalThursday($id, $date);
        $totalFriday = totalFriday($id, $date);
        
        include '../view/employeeTimesheet.php';
        break;
    case 'save_emp_hours':
        
        $empId = filter_input(INPUT_POST, 'EmpId');
        $date = filter_input(INPUT_POST, 'date');
        $saturday = filter_input(INPUT_POST, 'saturday');
        $sunday = filter_input(INPUT_POST, 'sunday');
        $monday = filter_input(INPUT_POST, 'monday');
        $tuesday = filter_input(INPUT_POST, 'tuesday');
        $wednesday = filter_input(INPUT_POST, 'wednesday');
        $thursday = filter_input(INPUT_POST, 'thursday');
        $friday = filter_input(INPUT_POST, 'friday');
        $jobOrders = filter_input(INPUT_POST, 'jobOrders');
        
        $NoOfHrs = filter_input(INPUT_POST, 'noOfHrs');
        $TotalOfWeeklyTotals = filter_input(INPUT_POST, 'totalOfWeeklyTotals');
        
        $sum = $NoOfHrs + $TotalOfWeeklyTotals;
        
        $totsat = filter_input(INPUT_POST, 'totsat');
        $totsun = filter_input(INPUT_POST, 'totsun');
        $totmon = filter_input(INPUT_POST, 'totmon');
        $tottues = filter_input(INPUT_POST, 'tottues');
        $totwed = filter_input(INPUT_POST, 'totwed');
        $totthurs = filter_input(INPUT_POST, 'totthurs');
        $totfri = filter_input(INPUT_POST, 'totfri');
        
        $finaltotsat = $totsat + $saturday;
        $finaltotsun = $totsun + $sunday;  
        $finaltotmon = $totmon + $monday;  
        $finaltottues = $tottues + $tuesday;  
        $finaltotwed = $totwed + $wednesday;  
        $finaltotthurs = $totthurs + $thursday;  
        $finaltotfri = $totfri + $friday;  
        
        if($finaltotsat > 10 ||$finaltotsun > 10 || $finaltotmon > 10 || $finaltottues > 10 || $finaltotwed > 10 || $finaltotthurs > 10 || $finaltotfri > 10){
            $error = "NO employee can work MORE than 10 hours per Day! ";
            include '../view/error.php';
        }
        else if($sum > 49){
            $error = "NO employee can work MORE than 49 hours per Week! ";
            include '../view/error.php';
        }
        else if($saturday > 12 || $sunday > 12 || $monday > 12 || $tuesday > 12 || $wednesday > 12 || $thursday > 12 || $friday > 12 ){
            $error = "NO employee can work MORE than 12 hours per day! ";
            include '../view/error.php';
        }else if($jobOrders == NULL){
            $error = "NO Jobsheet Order can be empty! ";
            include '../view/error.php';
        }else if(checkIfJobOrderExists($jobOrders) == FALSE){
            $error = "This Job Order does not exist! ";
            include '../view/error.php';
        }
        else if(jobOrderAlreadyExists($empId, $date, $jobOrders) == TRUE){
            $error = "Job ORDER Already Exists within the same Timesheet. ";
            include '../view/error.php';
        }
        else{
        $SaveEmpHours = Save_Emp_Hours($empId, $date, $saturday, $sunday, $monday, $tuesday, $wednesday, $thursday, $friday, $jobOrders);
        header("Location: .?action=enter_emp_hours&id=" . $empId . "&date=" . $date);
        }
   
        break;
        
    case 'delete_timesheet':
        $empId = filter_input(INPUT_POST, 'empId', FILTER_VALIDATE_INT);
        $id = filter_input(INPUT_POST, 'id', FILTER_VALIDATE_INT);
        
        $date = filter_input(INPUT_POST, 'weekEnding');
        
        $deleteTimesheet = delete_timesheet($id, $empId);
        
        
//        include '../view/employeeTimesheet.php';
        
        header("Location: .?action=enter_emp_hours&id=" . $empId . "&date=" . $date);
        break;
    default :
        echo"Unknown action".$action;
        break;
    
}

?>