$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateFundForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "FundAPI", 
type : type, 
data : $("#formFund").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onFundSaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidFundIDSave").val($(this).data("fundid")); 
 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#amount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#phone").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#nic").val($(this).closest("tr").find('td:eq(5)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "FundAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("fundid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onFundDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateFundForm() 
{ 
// Name
if ($("#name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// Address
if ($("#address").val().trim() == "") 
 { 
 return "Insert Address."; 
 } 
//Amount-------------------------------
if ($("#amount").val().trim() == "") 
 { 
 return "Insert Amount."; 
 } 
// is numerical value
var tmpAmount = $("#amount").val().trim(); 
if (!$.isNumeric(tmpAmount)) 
 { 
 return "Insert a numerical value for Item Price."; 
 } 
// convert to decimal price
 $("#amount").val(parseFloat(tmpAmount).toFixed(2)); 
// Email------------------------
if ($("#email").val().trim() == "") 
 { 
 return "Insert Email."; 
 } 
//Phone------------------------
if ($("#phone").val().trim() == "") 
 { 
 return "Insert Phone."; 
 } 
//NIC------------------------
if ($("#nic").val().trim() == "") 
 { 
 return "Insert NIC."; 
 } 
return true; 
}

function onFundSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divFundGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidFundIDSave").val(""); 
$("#formFund")[0].reset(); 
}


function onFundDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divFundGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
