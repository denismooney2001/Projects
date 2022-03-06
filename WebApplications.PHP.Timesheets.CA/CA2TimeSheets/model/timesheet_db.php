<?php
    function Save_Emp_Hours($empId, $date, $saturday, $sunday, $monday, $tuesday, $wednesday, $thursday, $friday, $jobOrders){
        global $db;
        
        $query = "INSERT INTO timesheets(id, empId, jobSheet, weekEnding, monday, tuesday, wednesday, thursday, friday, saturday, sunday)" 
                ." VALUES (NULL, :empId, :jobOrders, :date, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :sunday)";
        $statement = $db->prepare($query);
        $statement->bindValue(":empId", $empId);
        $statement->bindValue(":jobOrders", $jobOrders);
        $statement->bindValue(":date", $date);
        $statement->bindValue(":monday", $monday);
        $statement->bindValue(":tuesday", $tuesday);
        $statement->bindValue(":wednesday", $wednesday);
        $statement->bindValue(":thursday", $thursday);
        $statement->bindValue(":friday", $friday);
        $statement->bindValue(":saturday", $saturday);
        $statement->bindValue(":sunday", $sunday);
        
        $statement->execute();
        $save_timesheet = $statement->fetchAll();
        $statement->closeCursor();

        return $save_timesheet;
        
    }
    function get_employee_timesheet($id, $date){
            global $db;

            $query = "SELECT * FROM timesheets WHERE empId = :id AND weekEnding = :date ORDER BY empId DESC";
            $statement = $db->prepare($query);
            $statement->bindValue(":id", $id);
            $statement->bindValue(":date", $date);
            $statement->execute();
            $employee_timesheet = $statement->fetchAll();
            $statement->closeCursor();

            return $employee_timesheet;
        }
    function delete_timesheet($id, $empId){
        global $db;

        $query = "DELETE FROM timesheets WHERE empId = :empId AND id = :id";
        $statement = $db->prepare($query);
        $statement->bindValue(":empId", $empId);
        $statement->bindValue(":id", $id);
        $statement->execute();
        $employee_timesheet = $statement->fetchAll();
        $statement->closeCursor();
        
        return;
    }
    function totalSaturday($id, $date){
        global $db;
        $query = "SELECT SUM(saturday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalSaturday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalSaturday;
    }
    function totalSunday($id, $date){
        global $db;
        $query = "SELECT SUM(sunday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalSunday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalSunday;
    }
    function totalMonday($id, $date){
        global $db;
        $query = "SELECT SUM(monday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalMonday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalMonday;
    }
    function totalTuesday($id, $date){
        global $db;
        $query = "SELECT SUM(tuesday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalTuesday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalTuesday;
    }
    function totalWednesday($id, $date){
        global $db;
        $query = "SELECT SUM(wednesday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalWednesday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalWednesday;
    }
    function totalThursday($id, $date){
        global $db;
        $query = "SELECT SUM(thursday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalThursday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalThursday;
    }
    function totalFriday($id, $date){
        global $db;
        $query = "SELECT SUM(friday) FROM timesheets WHERE empId = :id AND weekEnding = :date";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        $statement->bindValue(":date", $date);
        $statement->execute();
        $TotalFriday = $statement->fetch();
        $statement->closeCursor();
        
        return $TotalFriday;
    }
    function jobOrderAlreadyExists($empId, $date, $jobOrders){
        global $db;
        $query1 = "SELECT * FROM timesheets WHERE empId = :empId AND weekEnding = :date AND jobSheet = :jobOrders";
        $statement1 = $db->prepare($query1);
        $statement1->bindValue(":empId", $empId);
        $statement1->bindValue(":jobOrders", $jobOrders);
        $statement1->bindValue(":date", $date);
        $statement1->execute();
        $rowCheck = $statement1->rowCount();
        
        if($rowCheck > 0){
           return TRUE;
        }
        else{
            return FALSE;
        }
    }
    function checkIfJobOrderExists($jobOrders){
        global $db;
        $query1 = "SELECT * FROM joborders WHERE jobOrderNo = :jobOrders";
        $statement1 = $db->prepare($query1);
        $statement1->bindValue(":jobOrders", $jobOrders);
        $statement1->execute();
        $rowCheck = $statement1->rowCount();
        
        if($rowCheck > 0){
           return TRUE;
        }
        else{
            return FALSE;
        }
    }
    
    
        
        