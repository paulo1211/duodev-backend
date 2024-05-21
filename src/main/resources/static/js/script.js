document.getElementById('inputCPF').addEventListener('input', function(e){
    let cpf = e.target.value.replace(/\D/g, ''); // Remove tudo que não é número
    if (cpf.length > 11) {
        cpf = cpf.slice(0, 11); // Limita a 11 caracteres
    }
    // Insere pontos e traço conforme digitação
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

    if (cpf.length === 14) {
        if (!validarCPF(cpf)) {
            e.target.classList.add('inputInvalidado');
        }else{
            e.target.classList.remove('inputInvalidado');
        }
    }

    e.target.value = cpf;
})

function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g,'');
    if (cpf === '') return false;
    // Elimina CPFs invalidos conhecidos
    if (cpf.length !== 11 ||
        cpf === '00000000000' ||
        cpf === '11111111111' ||
        cpf === '22222222222' ||
        cpf === '33333333333' ||
        cpf === '44444444444' ||
        cpf === '55555555555' ||
        cpf === '66666666666' ||
        cpf === '77777777777' ||
        cpf === '88888888888' ||
        cpf === '99999999999')
        return false;
    // Valida 1o digito
    let add = 0;
    for (let i=0; i < 9; i ++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    let rev = 11 - (add % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(9)))
        return false;
    // Valida 2o digito
    add = 0;
    for (let i = 0; i < 10; i ++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(10)))
        return false;
    return true;
}
document.addEventListener('DOMContentLoaded', function(){
    document.getElementById('inputEmail').addEventListener('input', function(e){
        let email = e.target.value;
        if (!validarEmail(email)) {
            e.target.classList.add('inputInvalidado');
        }else{
            e.target.classList.remove('inputInvalidado');
        }
    })
})


function validarEmail(email) {
    let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    console.log(regex.test(email));
    return regex.test(email);
    
}
document.addEventListener('DOMContentLoaded', function(){
    document.getElementById('inputConfSenha').addEventListener('input', function(e){
        var senha = document.getElementById('inputSenha').value;
        var confirmarSenha = e.target.value;
        if (senha != confirmarSenha) {
            e.target.classList.add('inputInvalidado');
        }else{
            e.target.classList.remove('inputInvalidado');
        }
        
    })
})


document.getElementById('usuarioForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var usuario = {
        cpf: document.getElementById('cpf').value,
        email: document.getElementById('email').value,
        nome: document.getElementById('nome').value,
        senha: document.getElementById('senha').value,
        sexo: document.getElementById('sexo').value
    };


    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/usuarios', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 201) {
          alert('Usuário enviado com sucesso!');
          // Limpar o formulário, se necessário
        } else {
          alert('Ocorreu um erro ao enviar usuário.');
        }
      }
    };
    xhr.send(JSON.stringify(Object.fromEntries(usuario)));
});