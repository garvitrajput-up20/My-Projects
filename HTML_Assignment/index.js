function addData() {
  // Get the input field values
  var formData = document.getElementById('enrollmentForm');
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var website = document.getElementById("website").value;
  var imageLink = document.getElementById("imageLink").value;
  var gender = document.querySelector('input[name="gender"]:checked').value;
  var skills = "";
  var skillCheckboxes = document.querySelectorAll('input[name="skills"]:checked');
  for (var i = 0; i < skillCheckboxes.length; i++) {
    skills += skillCheckboxes[i].value + ", ";
  }
  skills = skills.substring(0, skills.length - 2);
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (formData == " ") {
    alert("Please fill out all fields");
  } else {
    if (!emailRegex.test(email)) {
      alert('Please enter a valid email address');
      return;
    } else {
      // Check if the website URL starts with "http://" or "https://"
      if (website.startsWith("http://")) {
        // Replace "http://" with "https://"
        website = website.replace("http://", "https://");
      } else if (!website.startsWith("https://")) {
        // Prepend "https://" to the beginning of the URL if no protocol is specified
        website = "https://" + website;
      }

      // Create a new row to hold the data
      var newRow = document.createElement("tr");

      // Add a class to the new row
      newRow.classList.add("new-row");

      // Hide the row initially using CSS opacity
      newRow.style.opacity = 0;

      // Create a cell for the description data
      var descriptionCell = document.createElement("td");
      descriptionCell.innerHTML = "<b>" + name + "</b><br>" + email + "<br><a href='" + website + "' target='_blank'>" + website + "</a><br>" + gender + "<br>" + skills;
      newRow.appendChild(descriptionCell);

      // Create a cell for the image data
      var imageCell = document.createElement("td");
      var image = document.createElement("img");
      image.src = imageLink;
      image.style.width = "100px";
      image.style.height = "100px";
      imageCell.appendChild(image);
      newRow.appendChild(imageCell);

      // Append the new row to the table body
      var tableBody = document.getElementById("dataTable");
      tableBody.appendChild(newRow);

      // Clear the form
      document.getElementById("name").value = "";
      document.getElementById("email").value = "";
      document.getElementById("website").value = "";
      document.getElementById("imageLink").value = "";
      document.querySelector('input[name="gender"]:checked').checked = false;
      var skillCheckboxes = document.querySelectorAll('input[name="skills"]:checked');
      for (var i = 0; i < skillCheckboxes.length; i++) {
        skillCheckboxes[i].checked = false;
      }
      // Fade in the new row using CSS animation
      setTimeout(function () {
        newRow.style.opacity = 1;
      }, 100);
    }
  }
}