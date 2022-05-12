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

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

function App() {

    const [isLogin, setIsLogin] = useState(false);

    const loginCallback = (isLogin) => {
        setIsLogin(isLogin);
    }

    useEffect(() =>
    {
        console.log(isLogin, "islogin")
        console.log("1.")

        const res = _http.get("/member/check");

        res.then((result) => {

            if(result.status === 200){
                if(result.data){
                    console.log("true")
                    setIsLogin(true);
                }else{
                    console.log("false")
                    setIsLogin(false);
                }
            }
        })

        console.log("2.")

    }, [])

    return (
    <div>
        <BrowserRouter>
            <Routes>
                {isLogin ?
                    <Route path="/" element={<Navigation loginCallback={loginCallback}/>}>
                        <Route path="/" element={<Main/>} />
                        <Route path="/mypage" element={<MyPage/>} />
                    </Route>
                    :
                    <Route path="/" element={<LayOut/>}>
                        <Route path="/" element={<Login loginCallback={loginCallback}/>}/>
                        <Route path="/sign" element={<Sign/>} />
                    </Route>
                }
            </Routes>
        </BrowserRouter>
    </div>
    );
}

export default App;
