import React, {useEffect} from "react";
import axios from "axios";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

function MyPage (){

    const getMemberInfo = () => {

        const membername = document.querySelector('#membername').value;
        const name = document.querySelector('#name').value;

        return {
            membername,
            name
        };
    }

    const getUserDetail = () => {

        const res = _http.get("/member/");

        res.then((result) => {
            console.log(result, "result");
            document.querySelector('#membername').value = result.data.membername;
            document.querySelector('#name').value = result.data.name;
        })
    }

    const onClickMemberUpdate = () => {

        const memberData = JSON.stringify(getMemberInfo());

        const res = _http.put("/member/", memberData);

        res.then((result) => {
            console.log(result, "result");
        })
    }

    useEffect(() => {
        getUserDetail();
    }, [])

    return(
        <Container>
            <div className="row d-flex justify-content-center align-items-center h-100">
                <div className="col-md-4">
                    <h2>마이페이지</h2>

                    <div className="form-group">
                        <label>아이디</label>
                        <input
                            type="text"
                            className="form-control"
                            id="membername"
                            name="membername"
                            readOnly={true}
                        />
                    </div>

                    <div className="form-group">
                        <label>이름</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            name="name"
                        />
                    </div>

                    <Button onClick={() => {onClickMemberUpdate()}}>수정</Button>
                </div>
            </div>
        </Container>
    );
}

export default MyPage;