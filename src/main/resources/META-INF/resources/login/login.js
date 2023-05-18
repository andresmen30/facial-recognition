function openCam() {
    hideMessage();
    let photoCam = PF('photoCam');
    console.log(photoCam);
    if (photoCam.attached) {
        photoCam.detach();
        closeCam();
    } else {
        photoCam.attach();
        startProgressBar();
    }
}

function openCamSaveUser() {
    jQuery('#messagesUser').hide();
    let photoCam = PF('photoCamUser');
    if (photoCam.attached) {
        photoCam.detach();
        closeCamUser();
    } else {
        photoCam.attach();
    }
}

function photCamCapture() {
    PF('photoCamUser').capture();
    PF('buttonEye').enable();
}

function captureUser() {
    let buttonEye = PF('buttonEye');
    buttonEye.disable();
}

function captureEye() {
    let buttonEye = PF('buttonCapture');
    buttonEye.disable();
}


function closeCam() {
    hideMessage();
    PF('pbClient').setValue(0);
    PF('pbClient').cancel();
    PF('photoCam').detach();
}

function onloadRegisterUser() {
    closeCamUser();
}

function closeCamUser() {
    PF('photoCamUser').detach();
}

function startProgressBar() {
    let delay = 1250;
    let increment = 25;
    let func = setTimeout(function request() {
        let photoCapture = PF('photoCam');
        if (photoCapture.attached) {
            let pbClient = PF('pbClient'),
                oldValue = pbClient.getValue(),
                newValue = oldValue + increment;
            pbClient.setValue(pbClient.getValue() + increment);
            if (newValue === 100) {
                console.log("Se prepara para tomar captura");
                photoCapture.capture();
                console.log("Ya se tomo la captura...");
                clearTimeout(func);
            } else if (newValue < 100) {
                func = setTimeout(request, delay);
            }
        }
    }, delay);
}

function errorFaceId() {
    PF('pbClient').setValue(0);
    PF('pbClient').cancel();
    startProgressBar();
}

function successFaceId() {
    PF('pbClient').cancel();
    startProgressBar();
}

function succesRegister() {
    PF('buttonCapture').disable();
    PF('buttonEye').disable();
    closeCamUser();
}

function retryRegisterPhotoCam() {
    let delay = 4000;
    let func = setTimeout(function request() {
        jQuery('#messagesUser').hide();
        clearTimeout(func);
    }, delay);
}


function clearImage() {
    PF('buttonCapture').disable();
    PF('buttonEye').enable();
}

function hideMessage() {
    jQuery('#formIniciarSesion\\:messages').hide();
    jQuery('#formRegisterUser\\:messagesSave').hide();
}




