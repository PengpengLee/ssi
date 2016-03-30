var Login = function() {

    var handleLogin = function() {
    	/** 页面初始化时为表单对象赋值 */
		if (window.localStorage) {
			var username = localStorage.getItem('username');
			var password = localStorage.getItem('password');
			if(username){
				$(".login-form [name='username']").val(username);
			}
			if(password){
				$(".login-form [name='password']").val(password);
			}
		} else {
			// TODO 从cookie中获取用户名密码回填到表单对象
		}

        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
            	$(".alert.alert-danger").hide();
        		/* 获取表单信息并存储到本地 */
        		var username = $(".login-form [name='username']").val(); // 用户名
        		var password = $(".login-form [name='password']").val(); // 密码
        		var checked = $(':checkbox', form).attr('checked'); // 记住密码checkbox
        		// Safari, in Private Browsing Mode, looks like it supports localStorage but all calls to setItem
        		// throw QuotaExceededError. We're going to detect this and just silently drop any calls to setItem
        		// to avoid the entire page breaking, without having to do a check at each usage of Storage.
        		if (typeof localStorage === 'object') {
        		    try {
        		        localStorage.setItem('username', username);
        		        if (checked) { // 选择记住密码
        					localStorage.setItem('password', password);
        					localStorage.setItem('remember', checked); // 记住密码checkbox
        				} else {
        					localStorage.removeItem('remember');
        					//localStorage.removeItem('password');
        				} 
        		    } catch (e) {
        		        Storage.prototype._setItem = Storage.prototype.setItem;
        		        Storage.prototype.setItem = function() {};
        		        Msg.alert('您正在使用无痕浏览，将无法使用本地存储功能保存用户名密码');
        		    }
        		} else {
        			// TODO 将用户名信息存储到cookie
        		}
    			// 异步提交表单 
    			$.ajax({
    				url : ctx + '/doLogin.action',
    				type : 'POST',
    				data : {
    					username : username,
    					password : password = md5(password) // md5加密
    				},
    				dataType : 'json',
    				success : function (msg){
    					$("#sp_callback").html(msg.msg);
    					if(msg.key == 'success'){
    						$("#sp_callback").parent().removeClass("alert-danger").addClass("alert-success").show();
    						window.location.href = ctx + '/index.action';
    					} else {
    						// $("#sp_callback").parent().show();
    						Msg.alert(msg.msg);
    					}
    				}, error : function(msg){
    					Msg.alert('网络异常，请刷新重试');
    				}
    			});
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    };

    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                email: {
                    required: true,
                    email: true
                }
            },

            messages: {
                email: {
                    required: "Email is required."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   

            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function() {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    };

    var handleRegister = function() {

        function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='../../assets/global/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }

        if (jQuery().select2) {
	        $("#select2_sample4").select2({
	            placeholder: '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
	            allowClear: true,
	            formatResult: format,
	            formatSelection: format,
	            escapeMarkup: function(m) {
	                return m;
	            }
	        });


	        $('#select2_sample4').change(function() {
	            $('.register-form').validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
	        });
    	}

        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                mobile: {
                	required: true,
                	mobile: true
                },
                email: {
                    required: true,
                    email: true
                },
                address: {
                    required: true
                },
                city: {
                    required: true
                },
                country: {
                    required: true
                },

                username: {
                    required: true
                },
                password: {
                    required: true
                },
                rpassword: {
                    equalTo: "#register_password"
                },

                tnc: {
                    required: true
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                tnc: {
                    required: "Please accept TNC first."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
            	//alert('12');
            	// TODO checkForm <span class="help-block" id="name-error">This field is required.</span>
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler: function(form) {
            	// 异步提交表单 
    			$.ajax({
    				url : $("#ctx").val() + '/register.action',
    				type : 'POST',
    				data : $(form).serialize(),
    				dataType : 'json',
    				success : function (msg){
    					$("#sp_register_callback").html(msg.msg);
    					if(msg.key == 'success'){
    						$("#sp_register_callback").parent().removeClass("alert-danger").addClass("alert-success").show();
    						window.location.href = ctx + '/login.action';
    					} else {
    						Msg.alert(msg.msg);
    					}
    				}, error : function(msg){
    					Msg.alert('网络异常，请刷新重试');
    				}
    			});
            }
        });

        $('.register-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function() {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
    };

    return {
        //main function to initiate the module
        init: function() {

            handleLogin();
            handleForgetPassword();
            handleRegister();

        }

    };

}();