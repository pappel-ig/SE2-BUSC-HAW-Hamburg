import {User} from "../Frame";
import React from "react";
import {Navigate} from "react-router-dom";
import axios from "axios";

const budget = 0.666; //Hier Budget
const filters: string[] = [];

/*Testfilter*/
filters.push("vegan");
filters.push("vegetarisch");
filters.push("enthält keine laktosehaltigen Lebensmittel");

type ProfileProps = {
    user: User,
    loginStateChanged: Function
}

type ProfileState = {
    criteria: string[]
}

export class Profile extends React.Component<ProfileProps, ProfileState> {
    constructor(props: ProfileProps) {
        super(props)
        this.state = {
            criteria: []
        };
        this.logout = this.logout.bind(this);
    }

    logout() {
        axios.post('user/logout')
            .then(value => this.props.loginStateChanged());
    }

    render() {
        if (!this.props.user.loggedIn) return (<Navigate to={"/login"}/>)
        else return (
            <div className="boxinbox2">
                <h1>Hello {this.props.user.username}</h1>
                <button onClick={this.logout}>Logout</button>
                <br/>
                <h3>Budget</h3>
                <div className="budget-box">
                    <p>{budget}</p>
                </div>
                <br/>
                <h3>Aktive Filter</h3>
                <div className="boxinbox3">
                    <p>{this.state.criteria.join(" ,")}</p>
                </div>
            </div>
        )
    }
}
