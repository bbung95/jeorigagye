import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {BrowserRouter, Routes, Route, useNavigate} from "react-router-dom";
import Navigation from "./Routes/Navigation";
import Login from "./Routes/Login";
import MyPage from "./Routes/MyPage";
import Main from "./Routes/Main";
import Sign from "./Routes/Sign";
import axios from "axios";

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

    // 로그인 체크
    const checkUserLogin = async () => {

        let result = false;
        const res = _http.get("/member/check");

        res.then((result) => {

            console.log(result, "result")

            if(result.status === 200){
                if(result.data){
                    console.log("true")
                    result = true;
                }else{
                    console.log("false")
                    result = false;
                }
            }
        })

        loginCallback(result);
    }

    useEffect(() =>
    {
        console.log("1.")
        console.log("2.")

    }, [])

    return (
    <div>
        <BrowserRouter>
            <Routes>
                {isLogin ?
                    <Route path="/" element={<Navigation loginCallback={loginCallback}/>}>
                        <Route path="/main" element={<Main/>} />
                        <Route path="/mypage" element={<MyPage/>} />
                    </Route>
                    :
                    <>
                        <Route path="/" element={<Login loginCallback={loginCallback} checkUserLogin={checkUserLogin}/>}/>
                        <Route path="/sign" element={<Sign/>} />
                    </>
                }
            </Routes>
        </BrowserRouter>
    </div>
    );
}

export default App;
