import React, {useEffect, useState} from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {useObserver} from "mobx-react";
import indexStore from "../modules/indexStore";

const LoginCheck = () => {

    const {loginStore} = indexStore();

    useEffect(() => {
        console.log("logincheck")
        loginStore.loginCheck();
    })

    return (
        <div>
        </div>
    );
}

export default LoginCheck;