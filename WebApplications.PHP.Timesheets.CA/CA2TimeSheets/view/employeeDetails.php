<?php
include 'header.php'; ?>
<main>
    <section>
        <br>
     
        <h1>Enter <u>Employee Name</u> and <u>Date</u></h1>
        <div id="EmployeeForm">
            
            <form id="selectEmployee" action="index.php" method="POST" id="search_employee">
                <input type="hidden" name="action" value="enter_emp_hours" />
                <div id="employeename">
                    <label>Name / Employee No.</label>
                    <input list="browsers" name="id" required>
                    <datalist id="browsers">
                    <?php foreach($employees as $employee){ ?>
                        <option name="id" value="<?php echo $employee['id']; ?>"><?php echo $employee['firstName']; ?> <?php echo $employee['lastName']; ?></option>
                    <?php } ?>      
                    </datalist>
                </div>
                <div id="employeedate">
                    <label>Enter Date</label> <br>
                    <input type="date" name="date" min="2007-02-17" max="2025-11-16" step="7" placeholder="Enter Date" required />
                </div>
                </div>
                <br>
                <label>&nbsp;</label>
                <br><br><br><br>
                <div id="submitdetails">
                    <input id="go" type="submit" value="Go" name="Go"/>
                </div>
            </form>
        
    </section>
</main>
<?php include 'footer.php'; ?>

