/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

	$(document).ready(function() {
		$("#logoutLink").on("click", function(e) {
			e.preventDefault();
			document.logoutForm.submit();
		});
	});

