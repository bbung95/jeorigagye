import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Navigation from "./Routes/Navigation";
import MyPage from "./Routes/MyPage";
import Main from "./Routes/Main";
import axios from "axios";
import LayOut from "./Routes/LayOut";
import Login from "./Routes/Login";
import Sign from "./Routes/Sign";
import {useObserver} from "mobx-react";
import indexStore from "./modules/indexStore";

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

    loginStore.loginCheck();
    console.log("sds")
    return (
    <div>
        <BrowserRouter>
            <Routes>
                {loginStore.isLogin ?
                    <Route path="/" element={<Navigation/>}>
                        <Route path="/" element={<Main/>} />
                        <Route path="/mypage" element={<MyPage/>} />
                    </Route>
                    :
                    <Route path="/" element={<LayOut/>}>
                        <Route path="/" element={<Login/>}/>
                        <Route path="/sign" element={<Sign/>} />
                    </Route>
                }
            </Routes>
        </BrowserRouter>
    </div>
    );
}

export default App;
