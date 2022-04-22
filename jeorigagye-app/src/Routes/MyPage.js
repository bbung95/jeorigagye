import React from "react";
import axios from "axios";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

function MyPage (){

    function welcome() {

        console.log('click')
        axios({
            url: "http://localhost:8080/home",
            method: "GET",
        }).then((res) => {
            console.log(res.data);
        });
    }

    return(
        <Container>
            <div className="row d-flex justify-content-center align-items-center h-100">
                <div className="col-md-4">
                    <h2>MyPage</h2>
                    <Button onClick={welcome}>연결테스트</Button>
                </div>
            </div>
        </Container>
    );
}

export default MyPage;