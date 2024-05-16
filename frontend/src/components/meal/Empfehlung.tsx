import React from "react";
import {User} from "../Frame";
import {Navigate} from "react-router-dom";

type EmpfehlungProps = {
    user: User
}

export class Empfehlung extends React.Component<EmpfehlungProps, {}> {
    render() {
        if (!this.props.user.loggedIn) return (<Navigate to={"/login"}/>)
        else return (
            <div>
                <h1>Tagesaktuelle Empfehlung der Mensa</h1>
            </div>
        )
    }
}