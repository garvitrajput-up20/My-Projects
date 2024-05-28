<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>User Registration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body class="bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 mt-5">
				<div class="card">
					<div class="card-header bg-success text-center fs-3">
						Register</div>
					<div class="card-body">
						<form method="post" action="fillform">
							

							<div class="mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" name="username" id="email">
							</div>
							<div class="mb-3">
								<label for="pass" class="form-label">Password</label> <input
									type="password" class="form-control" name="password"
									id="password">
							</div>
							<button type="submit" class="btn btn-primary">Register</button>
							<a href="login.jsp"><button type="button"
									class="btn btn-secondary">Login</button></a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>