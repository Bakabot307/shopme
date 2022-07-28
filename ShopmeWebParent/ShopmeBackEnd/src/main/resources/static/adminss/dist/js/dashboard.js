$(document).ready(function() {
			$(document).ready(function() {
	$("#buttonCancel").on("click", function() {
		window.location = moduleURL;
	});
        })

	$("#fileImage").change(function() {
		if (!checkFileSize(this)) {
			return;
		}
		
		showImageThumbnail(this);				
	});
});

function showImageThumbnail(fileInput) {
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		$("#thumbnail").attr("src", e.target.result);
	};
	
	reader.readAsDataURL(file);
}

function checkFileSize(fileInput) {
	fileSize = fileInput.files[0].size;
	MAX_FILE_SIZE = 102400;
	if (fileSize > MAX_FILE_SIZE) {
		fileInput.setCustomValidity("You must choose an image less than " + MAX_FILE_SIZE + " bytes!");
		fileInput.reportValidity();
		
		return false;
	} else {
		fileInput.setCustomValidity("");
		
		return true;
	}	
}
		function checkUnique(form) {
			url_email = "/admin/users/check_email";
                        url_username = "/admin/users/check_username";
			userEmail = $("#email").val();
                        username = $("#username").val();
			csrfValue = $("input[name=_csrf]").val();
			userId = $("#id").val();
			params = {
					id: userId,
				email : userEmail,
                                username: username,
				_csrf : csrfValue
			};
			  const errorEmail = document.getElementById("error-email") 
                          const errorUsername = document.getElementById("error-username") 
                           const error = document.getElementById("error") 
			$.post(url_email, params, function(response) {
                            console.log(response);
				if (response === "bothOk") {                                        
					form.submit();
				} else if (response === "bothDuplicated") {
					errorEmail.innerHTML=("There is another user registered with email " + userEmail);
                                        errorUsername.innerHTML=("There is another user registered with username " + username);       
                                 } else if (response === "eDuplicated") {
					errorEmail.innerHTML=("There is another user registered with email " + userEmail); 
                                        errorUsername.innerHTML=(""); 
                                         } else if (response === "nDuplicated") {
					errorUsername.innerHTML=("There is another user registered with username " + username);   
                                        errorEmail.innerHTML=(""); 
				} else{
					error.innerHTML=("Unknown response from server");
				}

			}).fail(function(){
				error.innerHTML=("Could not connect to server");
			});
                        
                       
			return false;
		}
                

	
	
	function checkUniqueCat(form) {
		catId = $("#id").val();
		catName = $("#name").val();
		catAlias = $("#alias").val();
		
		csrfValue = $("input[name='_csrf']").val();
		
		url = "/admin/category/check_unique";
		
		params = {id: catId, name: catName, alias: catAlias, _csrf: csrfValue};
		const errorName = document.getElementById("error-name") 
                          const errorAlias = document.getElementById("error-alias") 
                           const error = document.getElementById("error") 
		$.post(url, params, function(response) {
			if (response == "OK") {
				form.submit();
			} else if (response == "DuplicateName") {
				errorName.innerHTML=("There is another category having same name " + catName);	
			} else if (response == "DuplicateAlias") {
				errorAlias.innerHTML=("There is another category having same alias " + catAlias);
			} else {
				error.innerHTML=("Unknown response from server");
			}
			
		}).fail(function() {
			error.innerHTML=("Could not connect to the server");
		});
		
		return false;
	}
     
		