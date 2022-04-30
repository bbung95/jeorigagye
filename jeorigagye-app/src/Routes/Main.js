import React from "react";
import {Container, ListGroup, ListGroupItem} from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

function Main (){

    // function logout() {
    //     axios({
    //         url: "/user/logoutUser",
    //         method: "POST",
    //     }).then((res) => {
    //         localStorage.setItem("trip-token", "");
    //         loginCallback(res.data);
    //     });
    // }

    return(
      <Container>
          <div className="row d-flex justify-content-center align-items-center h-100">
              <div className="col-md-4">
                  <ListGroup>
                      <ListGroupItem header="Heading 1">Some body text</ListGroupItem>
                      <ListGroupItem header="Heading 2" href="#">
                          Linked item
                      </ListGroupItem>
                      <ListGroupItem header="Heading 3" bsStyle="danger">
                          Danger styling
                      </ListGroupItem>
                  </ListGroup>;
              </div>
          </div>
      </Container>
    );
}

export default Main;