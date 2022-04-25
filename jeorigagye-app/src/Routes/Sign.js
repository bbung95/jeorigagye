import React, { useState, useEffect } from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';

const _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8'
    },
    withCredentials: true
});

function Sign() {

    const navigate = useNavigate();

    const [password, setPassword] = useState("");
    const [membername, setMembername] = useState("");
    const [name, setName] = useState("");

    // const handleValidation = (event) => {
    //     let formIsValid = true;
    //
    //     // if (!email.match(/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/)) {
    //     //     formIsValid = false;
    //     //     setemailError("Email Not Valid");
    //     //     return false;
    //     // } else {
    //     //     setemailError("");
    //     //     formIsValid = true;
    //     // }
    //
    //     if (!password.match(/^[a-zA-Z]{8,22}$/)) {
    //         formIsValid = false;
    //         setpasswordError(
    //             "Only Letters and length must best min 8 Chracters and Max 22 Chracters"
    //         );
    //         return false;
    //     } else {
    //         setpasswordError("");
    //         formIsValid = true;
    //     }
    //
    //     return formIsValid;
    // };

    const registMemberSubmit = () => {

        const memberForm = JSON.stringify({
            membername,
            password,
            name
        })

        if(membername == ''){
            alert("아이디를 입력해주세요.");
        }

        if(password == ''){
            alert("비밀번호를 입력해주세요.");
        }

        if(name == ''){
            alert("이름을 입력해주세요.");
        }

        //if(handleValidation()){
        const res = _http.post("member/join", memberForm);

        console.log(res, "res")

        if(res != null){
            alert("회원가입이 완료되었습니다.");
            navigate("/")
        }
        //}

    };

    return (
            <Container className="h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-md-4">
                        <form id="loginform">
                            <div className="form-group">
                                <label>아이디</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="membername"
                                    name="membername"
                                    aria-describedby="emailHelp"
                                    placeholder="아이디를 입력하세요"
                                    onChange={(event) => setMembername(event.target.value)}
                                />
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
                            </div>
                            <div className="form-group">
                                <label>이름</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="name"
                                    name="name"
                                    placeholder="이름을 입력하세요"
                                    onChange={(event) => setName(event.target.value)}
                                />
                            </div>
                            <button type="button" className="btn btn-primary" onClick={() => registMemberSubmit()}>
                                등록
                            </button>
                            <button type="button" className="btn btn-primary" onClick={() => navigate("/")}>
                                취소
                            </button>
                        </form>
                    </div>
                </div>
            </Container>
    );
}

export default Sign;