import React, {useState, useEffect, useLayoutEffect} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import axios from "axios";
import {useObserver} from "mobx-react";
import indexStore from "./modules/indexStore";
import Router from "./Routes/Router";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

const App = () => {

    const {loginStore} = indexStore();

    useLayoutEffect(() => {

        loginStore.loginCheck();

        console.log(loginStore.isLogin);
        console.log('app.js login check');
console.log(23)
    }, [])

    return (
    <div>
        <Router></Router>
    </div>
    );
}

export default App;
