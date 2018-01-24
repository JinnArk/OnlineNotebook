function validata(){
  if($("#username").val()==""){
  alert("请输入名字");
  return false;
  }
  if($("#password").val()==""){
  alert("请输入密码");
  return false;
  }
  if($("#telephone").val()==""){
  alert("请输入电话号码");
  }
  if($("#email").val()==""){
  $("#email").val("shuangping@163.com");
  }
}
function isInteger(obj){
  reg=/^[-+]?\d+$/;
  if(!reg.test(obj)){
  $("#test").html("<b>Please input correct figures</b>");
  }else{
  $("#test").html("");
  }
}
function isEmail(obj){
  reg=/^\w{3,}@\w+(\.\w+)+$/;
  if(!reg.test(obj)){
  $("#test").html("<b>请输入正确的邮箱地址</b>");
  }else{
  $("#test").html("");
  }
}
function isString(obj){
  reg=/^[a-z,A-Z]+$/;
  if(!reg.test(obj)){
  $("#test").html("<b>只能输入字符</b>");
  }else{
  $("#test").html("");
  }
}
function isTelephone(obj){
  reg=/^(\d{3,4}\-)?[1-9]\d{6,7}$/;
  if(!reg.test(obj)){
  $("#test").html("<b>请输入正确的电话号码！</b>");
  }else{
  $("#test").html("");
  }
}
function isMobile(obj){
  reg=/^(\+\d{2,3}\-)?\d{11}$/;
  if(!reg.test(obj)){
  $("#test").html("请输入正确移动电话");
  }else{
  $("#test").html("");
  }
}