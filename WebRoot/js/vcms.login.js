
$(document).ready(function() {
	alert(dd);
	/* var speed = 400;
	
	$("#tologin").click(function(){
		$("#loginbox").fadeTo(speed, 1).css('z-index','200');
	});
	
	$("#logout").click(function(){
		location.reload(true);
	});
	
	$("#to-recover").click(function(){
		$("#loginbox").attr("style", "top:23%;display:block;height:340px;");
	});
	
	$("#to-login").click(function(){
		$("#loginbox").attr("style", "top:30%;display:block;height:200px;");
	});
	
	$("#login").click(function(){
				
		var account = $("#account").val().trim();
		var password = $("#password").val().trim();
				
		if(account != "" && password != "")
		{
			$.ajax({
						
				type: "POST",
				url: "/vcms/login.action",
				data: {"account": account, "password": password},
				dataType: "json",
				success: function(data){
							
					if(data == "1"){  
							
						alert("登录成功");
				        location.reload(true);
					} else {
						alert("账号密码错误");
					}	
				}
			});
		} else {
			alert("请输入帐号密码");
		}
	}); 
	
	$("#register").click(function(){
		
		var account = $("#registerAccount").val().trim();
		var password1 = $("#registerPassword1").val().trim();
		var password2 = $("#registerPassword2").val().trim();
		var name = $("#user_name").val().trim();
				
		if(account == "" || password1 == "" || password2 == "" || name == "")
		{
			alert("请输入完整信息");
		}
		else if(password1 != password2) {
			alert("两次密码不一致");
		}
		else {
				
			$.ajax({
						
				type: "POST",
				url: "/vcms/register.action",
				data: {"account": account, "password": password1, "name": name},
				dataType: "json",
				success: function(data){
							
					if(data == "1"){  
							
						alert("注册成功，已登录");
				        location.reload(true);
				    } else {
						alert("注册失败");
					}	
				}
			});
		}
	}); */
});