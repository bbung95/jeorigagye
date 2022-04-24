import React, { useState, useEffect } from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Sign from "./Sign.js";

import axios from 'axios';
const _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8'
    },
    withCredentials: true
});

function Login({loginCallback}) {

    const [loginForm, setLoginForm] = useState(true);

    const [password, setPassword] = useState("");
    const [membername, setMembername] = useState("");
    const [passwordError, setpasswordError] = useState("");
    const [emailError, setemailError] = useState("");

    const handleValidation = (event) => {
        let formIsValid = true;

        // if (!email.match(/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/)) {
        //     formIsValid = false;
        //     setemailError("Email Not Valid");
        //     return false;
        // } else {
        //     setemailError("");
        //     formIsValid = true;
        // }

        if (!password.match(/^[a-zA-Z]{8,22}$/)) {
            formIsValid = false;
            setpasswordError(
                "Only Letters and length must best min 8 Chracters and Max 22 Chracters"
            );
            return false;
        } else {
            setpasswordError("");
            formIsValid = true;
        }

        return formIsValid;
    };

    const loginSubmit = () => {

        const memberForm = JSON.stringify({
            membername,
            password
        })

        //if(handleValidation()){

            const res = _http.post("member/join", memberForm);

            console.log(res);
        //}

        loginCallback(true);
    };

    const loginformCallback = (loginForm) => {
        setLoginForm(loginForm)
    }

    console.log(loginForm, "loginForm")

    return (
        <div>
            {loginForm ?
                <Container className="h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-4">
                            <form id="loginform">
                                <div className="form-group">
                                    <label>아이디</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="username"
                                        name="username"
                                        aria-describedby="emailHelp"
                                        placeholder="아이디를 입력하세요"
                                        onChange={(event) => setMembername(event.target.value)}
                                    />
                                    <small id="emailHelp" className="text-danger form-text">
                                        {emailError}
                                    </small>
                                </div>
                                <div className="form-group">
                                    <label>비밀번호</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        name="password"
                                        placeholder="비밀번호를 입력하세요"
                                        onChange={(event) => setPassword(event.target.value)}
                                    />
                                    <small id="passworderror" className="text-danger form-text">
                                        {passwordError}
                                    </small>
                                </div>
                                <div className="form-group form-check">
                                    <input
                                        type="checkbox"
                                        className="form-check-input"
                                        id="exampleCheck1"
                                    />
                                    <label className="form-check-label">아이디 저장</label>
                                </div>
                                <button type="button" className="btn btn-primary" onClick={loginSubmit}>
                                    로그인
                                </button>
                                <button type="button" className="btn btn-primary" onClick={() => setLoginForm(false)}>
                                    회원가입
                                </button>
                            </form>
                        </div>
                    </div>
                </Container>
                :
                <Sign loginformCallback={loginformCallback}/>
            }
        </div>
    );
}

export default Login;