<div id="register" class="col-md-8 col-xs-12 col-sm-8 col-lg-8 rounded border-left">
    <h3>Register</h3>
    <form action="controller" method="post" autocomplete="off">
        <input name="action" value="registerBasicUser" type="hidden"/>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
        </div>
        <small>Password have to be 8 characters, Smaller Case, Upper Case and Special Characters(!@#$)</small>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="password">Password</label>
                <input type="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" class="form-control" name="password" id="password" placeholder="Password" required>
            </div>
            <div class="form-group col-md-6">
                <label for="conPassword">Confirm Password</label>
                <input type="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" class="form-control" id="conPassword" name="confirmPassword" placeholder="Confirm Password" required>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
        </div>
        <div class="form-group">
            <label for="userType">Type</label>
            <select name="userType" class="form-control" required>
                <option selected>Pick a Type</option>
                <option value="STUDENT">Student</option>
                <option value="TEACHER">Teacher</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-block rounded-pill">Register</button>
    </form>
</div>