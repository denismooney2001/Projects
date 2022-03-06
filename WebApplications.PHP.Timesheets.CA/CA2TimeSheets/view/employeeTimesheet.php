<?php
include 'header.php'; ?>

<main>
    <section>
        <br>
     
        <h1>Enter <u>Hours</u> and <u>Jobs Orders</u> completed</h1>
        <div id="wrap">
            <div id="employeeheading">
                <?php foreach($employee_info as $employee){ ?>
                <div id="employeeinformation1">
                    <p class="details"><u>Employee Name:</u> <span class="details2"><?php echo $employee['firstName']; ?> <?php echo $employee['lastName']; ?></span></p> 
                </div>
                <div id="employeeinformation2">
                    <p class="details"><u>Employee ID:</u> <span class="details2"><?php echo $employee['id']; ?></span></p>
                </div>
                <div id="employeeinformation3">
                    <p class="details"><u>Week Ending:</u> <span class="details2"><?php echo $date; ?></span></p>
                </div>
                
                <?php } ?>
            </div>
            
        </div>    
        
        <br /><br /><br /><br /><br />
        <form id="TimeSheet" action="." method="POST">
            <input type="hidden" name="action" value="save_emp_hours" />
                <?php foreach($employee_info as $employee){ ?>
                <input type="hidden" name="EmpId" value="<?php echo $employee['id']; ?>" />
                <input type="hidden" name="date" value="<?php echo $date; ?>" />
                <?php } ?>
                <div id="wrapTimesheet">
                    <table id="enterTimeSheet">
                        
                        <tr>
                            <th>Saturday</th>
                            <th>Sunday</th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Weekly Total</th>
                            <th>Job Order</th>
                        </tr>
                        <tr>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day" name="saturday" value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="sun" class="day" name="sunday"  value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="mon" class="day" name="monday"  value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="tue" class="day" name="tuesday"  value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="wed" class="day" name="wednesday"  value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="thurs" class="day" name="thursday"  value="0"/></td>
                            <td><input type="text" maxlength="2" size="2" id="fri" class="day" name="friday"  value="0"/></td>
                            <td><input style="font-weight: bold; border: none;text-align: center;" readonly="readonly" style="border:none" name="noOfHrs" type="text" maxlength="2" size="2" id="result"/></td>
                            <td><div class="ui-helper-clearfix"><input class="JobOrders"  size="6" type="text" name="jobOrders"  /></div></td>
                            <input type="hidden" name="totalOfWeeklyTotals" class="resultofTotalColumns" />
                            
                            <input type="hidden" name="totsat" value="<?php echo $totalSaturday[0] ?>" />
                            <input type="hidden" name="totsun" value="<?php echo $totalSunday[0] ?>" />
                            <input type="hidden" name="totmon" value="<?php echo $totalMonday[0] ?>" />
                            <input type="hidden" name="tottues" value="<?php echo $totalTuesday[0] ?>" />
                            <input type="hidden" name="totwed" value="<?php echo $totalWednesday[0] ?>" />
                            <input type="hidden" name="totthurs" value="<?php echo $totalThursday[0] ?>" />
                            <input type="hidden" name="totfri" value="<?php echo $totalFriday[0] ?>" />
                            <td><input id="saveTimesheet" type="submit" value="Save" /></td> 
                          
                        </tr>
                    </table>
        </form> 
                
                </div>
                
                
                <div class="wrapTimesheet2">
                    <form id="TimeSheet" action="." method="POST">
                        <?php foreach($employee_info as $employee){ ?>
                        <input type="hidden" name="EmpId" value="<?php echo $employee['id']; ?>" />
                        <input type="hidden" name="date" value="<?php echo $date; ?>" />
                        <?php } ?>
                    <input type="hidden" name="action" value="save_emp_hours" />
                    <table id="enterTimeSheet2">
                        
                        <tr>
                            <th>Headings</th>
                            <th>Enter Values</th>
                        </tr>
                        <tr>
                            <td>Saturday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="saturday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Sunday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="sunday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Monday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="monday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Tuesday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="tuesday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Wednesday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="wednesday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Thursday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="thursday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Friday</td>
                            <td><input type="text" maxlength="2" size="2" id="sat" class="day1" name="friday" value="0"/></td>
                        </tr>
                        <tr>
                            <td>Total</td>
                            <td><input style="font-weight: bold; border: none;text-align: center;background: transparent;" name="noOfHrs" readonly="readonly" style="border:none" type="text" maxlength="2" size="2" id="result1"/></td>
                        </tr>
                        <tr>
                            <td>Job Orders</td>
                            <td><div class="ui-helper-clearfix"><input class="JobOrders"  size="6" type="text" name="jobOrders"  /></div></td>
                        </tr>
                            <td>Save</td>
                            <input type="hidden" name="totalOfWeeklyTotals" class="resultofTotalColumns" />
                            
                            <input type="hidden" name="totsat" value="<?php echo $totalSaturday[0] ?>" />
                            <input type="hidden" name="totsun" value="<?php echo $totalSunday[0] ?>" />
                            <input type="hidden" name="totmon" value="<?php echo $totalMonday[0] ?>" />
                            <input type="hidden" name="tottues" value="<?php echo $totalTuesday[0] ?>" />
                            <input type="hidden" name="totwed" value="<?php echo $totalWednesday[0] ?>" />
                            <input type="hidden" name="totthurs" value="<?php echo $totalThursday[0] ?>" />
                            <input type="hidden" name="totfri" value="<?php echo $totalFriday[0] ?>" />
                            <td><input id="saveTimesheet" type="submit" value="Save" /></td>
                        </tr>
                    </table>
                    </form>
                </div>
            </form>
        <br /><br />
        <br />
        <h1>Job Orders Complete</h1>
        <?php foreach ($employee_timesheet_info as $empHrs) { ?>
        <div class="wrapTimesheet2">
        <table id="JobsCompleted2">
            
            <form method="POST" action = "." >
                <input type="hidden" name="action" value="delete_timesheet" />
            <tr>
                <th>Headings</th>
                <th>Values</th>
            </tr>
            <tr id="jobS">
                <td>Job Order</td>
                <td><u><?php echo $empHrs['jobSheet']; ?></u></td>
            </tr>
            <tr>
                <td>Saturday</td>
                <td><?php echo $empHrs['saturday']; ?></td>
            </tr>
            <tr>
                <td>Sunday</td>
                <td><?php echo $empHrs['sunday']; ?></td>
            </tr>
            <tr>
                <td>Monday</td>
                <td><?php echo $empHrs['monday']; ?></td>
            </tr>
            <tr>
                <td>Tuesday</td>
                <td><?php echo $empHrs['tuesday']; ?></td>
            </tr>
            <tr>
                <td>Wednesday</td>
                <td><?php echo $empHrs['wednesday']; ?></td>
            </tr>
            <tr>
                <td>Thursday</td>
                <td><?php echo $empHrs['thursday']; ?></td>
            </tr>
            <tr>
                <td>Friday</td>
                <td><?php echo $empHrs['sunday']; ?></td>
            </tr>
            <tr>
                <td>Weekly Total</td>
                <td><?php echo $empHrs['saturday'] + $empHrs['sunday'] + $empHrs['monday'] + $empHrs['tuesday'] + $empHrs['wednesday'] + $empHrs['thursday'] + $empHrs['friday'] ?></td>
            </tr>
            
            <tr>
                <td>Delete</td>
                <td><input class="deleteOption" id="delete"type="submit" value="Delete"/></td>
                <input type="hidden" name="weekEnding" value="<?php echo $empHrs['weekEnding']; ?>" />                
                <input type="hidden" name="id" value="<?php echo $empHrs['id']; ?>" />
                <input type="hidden" name="empId" value="<?php echo $empHrs['empId']; ?>" />
            </tr>
        </table>
        </div>    
        </form>
            <br />
        <?php } ?>
        
        <table id="JobsCompleted">
                <th>Saturday</th>
                <th>Sunday</th>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
                <th>Weekly Total</th>
                <th>Job Order</th>
                
            </tr>
            <?php foreach ($employee_timesheet_info as $empHrs) { ?>
            <tr>
            <form method="POST" action = "." >
                <input type="hidden" name="action" value="delete_timesheet" />
                <td><?php echo $empHrs['saturday']; ?></td>
                <td><?php echo $empHrs['sunday']; ?></td>
                <td><?php echo $empHrs['monday']; ?></td>
                <td><?php echo $empHrs['tuesday']; ?></td>
                <td><?php echo $empHrs['wednesday']; ?></td>
                <td><?php echo $empHrs['thursday']; ?></td>
                <td><?php echo $empHrs['friday']; ?></td>
                <td id="TotalofWeeklyTotals"><?php echo $empHrs['saturday'] + $empHrs['sunday'] + $empHrs['monday'] + $empHrs['tuesday'] + $empHrs['wednesday'] + $empHrs['thursday'] + $empHrs['friday'] ?></td>
                <td><?php echo $empHrs['jobSheet']; ?></td>
                <td id="delTag"><input class="deleteOption" id="delete"type="submit" value="Delete"/></td>
                <input type="hidden" name="weekEnding" value="<?php echo $empHrs['weekEnding']; ?>" />                
                <input type="hidden" name="id" value="<?php echo $empHrs['id']; ?>" />
                <input type="hidden" name="empId" value="<?php echo $empHrs['empId']; ?>" />
            </form>
            <?php } ?>
            </tr> 
            <tr>
            <form>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalSaturday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn"value="<?php echo $totalSunday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalMonday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalTuesday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalWednesday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalThursday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="TotalColumn" value="<?php echo $totalFriday[0] ?>" /></td>
                <td><input size="2" readonly="readonly" style="border: none; background: transparent;" class="resultofTotalColumns" /></td>
            </tr>
            </form>
        </table>
        <br />
    </section>
</main>
<?php include 'footer.php'; ?>

