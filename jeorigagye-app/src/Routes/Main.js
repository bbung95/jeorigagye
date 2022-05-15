import React, {useEffect} from "react";
import {Container, ListGroup, ListGroupItem, Card} from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

function Main (){

    const getSearchData = () => {

        return {
            "type" : "INCOME",
            "culPage" : "0"
        }
    }

    const searchAccountList = () => {

        const searchData = getSearchData();

        const res = _http.get(`/account/?type=${searchData.type}&culPage=${searchData.culPage}`);

        res.then((result) => {
            console.log(result, "result")
        })
    }

    useEffect(() => {
        console.log("호출")
        searchAccountList();
    })

    return(
        <>
          <Container>
              <div className="row d-flex justify-content-center align-items-center h-100">
                  <div className="col-md-4">

                      <div>
                        <Card body>This is some text within a card body.</Card>
                        <Card body>This is some text within a card body.</Card>
                        <Card body>This is some text within a card body.</Card>
                      </div>

                  </div>
              </div>
          </Container>
        </>
    );
}

export default Main;