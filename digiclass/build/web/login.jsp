<div id="login" class="col-xs-12 col-sm-4 col-md-4 col-lg-4 rounded border-right">
    <div class="text-center">
        <h3>Login</h3>
    </div>
    <hr>
    <form action="controller" method="post" autocomplete="off">
        <input name="action" value="login" type="hidden"/>
        <div class="form-group">
            <label class="sr-only" for="username">Username</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fa fa-user-circle" aria-hidden="true"></i></div>
                </div>
                <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
            </div>
        </div>
        <div class="form-group">
            <label class="sr-only" for="password">Password</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <div class="input-group-text"><i class="fas fa-lock"></i></div>
                </div>
                <input type="password" class="form-control" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" id="password" name="password" placeholder="Password" required>
            </div>
        </div>
        <div class="form-row text-center">
            <div class="form-group form-check col-md-6">
                <input type="checkbox" class="form-check-input" id="remember">
                <label class="form-check-label text-white" for="remember">Remember Me</label>
            </div>   
            <div class="form-group form-check col-md-6">
                <a href="resetPasswordFindEmail.jsp" class="text-white text-decoration-none">Forget Password?</a>
            </div>
        </div>

        <button type="submit" class="btn btn-primary btn-block rounded-pill">Submit</button>
    </form>
</div>

