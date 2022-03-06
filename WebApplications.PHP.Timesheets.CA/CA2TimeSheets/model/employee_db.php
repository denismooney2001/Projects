<?php
    function get_employeeDetails(){
        global $db;

        $query = "SELECT * FROM employees ORDER BY firstName ASC";
        $statement = $db->prepare($query);
        $statement->execute();
        $employee = $statement->fetchAll();
        $statement->closeCursor();

        return $employee;
    }
    function get_selected_employee($id){
        global $db;
        
        $query = "SELECT * FROM employees WHERE id = :id";
        $statement = $db->prepare($query);
        $statement->bindValue(":id", $id);
        
        try{
        $statement->execute();
        }
        catch(PDOException $ex){
            //redirect to an error page passing the error message
            header("Location: ..view/error.php?msg=" . $ex->getMessage());
            exit();
        }
        $employee_info = $statement->fetchAll();
        $statement->closeCursor();
        
        return $employee_info;
        
        
    }


