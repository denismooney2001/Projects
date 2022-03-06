<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require("../model/database.php");

$name = $_GET["term"];

if ($name != null) {
    $query = "SELECT * FROM joborders WHERE jobOrderNo LIKE :name";
    $statement = $db->prepare($query);
    $statement->bindValue(":name", $name."%", PDO::PARAM_STR);
    //$statement->bindValue(":region",$region,PDO::PARAM_STR);
    try {
        $statement->execute();
    }
    catch(PDOException $e) {
        echo $e->getMessage();
        exit();
    }
    $results = $statement->fetchAll();
    $statement->closeCursor();
    
    $job_Orders = array();
    
    foreach ($results as $result){
        $job_Orders[$result['id']] = $result['jobOrderNo'];
       ;
    }
    $response = json_encode($job_Orders);
    echo $response;
}


