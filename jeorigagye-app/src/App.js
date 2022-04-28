import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {BrowserRouter, Routes, Route, useNavigate} from "react-router-dom";
import Navigation from "./Routes/Navigation";
import Login from "./Routes/Login";
import MyPage from "./Routes/MyPage";
import Main from "./Routes/Main";
import Sign from "./Routes/Sign";

function App() {

    const [isLogin, setIsLogin] = useState(false);

    const loginCallback = (isLogin) => {
        setIsLogin(isLogin);
    }

    useEffect(() =>
    {
        //if(로그인 체크 api 서버 통신 성공하면) {
        // setLogin(true)
        // }else{
        // setLogin(false);
        // },

        // if(isLogin){
        //     navigate("/");
        // }

    },[])

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
                        <Route path="/" element={<Login loginCallback={loginCallback}/>}/>
                        <Route path="/sign" element={<Sign/>} />
                    </>
                }
            </Routes>
        </BrowserRouter>
    </div>
    );
}

export default App;
