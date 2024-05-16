import {User} from "../Frame";
import React from "react";
import {Navigate} from "react-router-dom";

const budget = 0.666; //Hier Budget
const filters: string[] = [];

/*Testfilter*/
filters.push("vegan");
filters.push("vegetarisch");
filters.push("enthält keine laktosehaltigen Lebensmittel");

type ProfileProps = {
    user: User,
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
    }

    render() {
        if (!this.props.user.loggedIn) return (<Navigate to={"/login"}/>)
        else return (
            <div className="boxinbox2">
                <h1>Max Mustermann</h1>
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
