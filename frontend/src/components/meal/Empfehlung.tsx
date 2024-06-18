import React from "react";
import {User} from "../Frame";
import {Navigate} from "react-router-dom";
import axios from "axios";
import {MenuItem} from "./Tagesplan";
import {Meal} from "./Meal";

type EmpfehlungProps = {
    user: User
}

type EmpfehlungState = {
    meals: MenuItem[]
}

export class Empfehlung extends React.Component<EmpfehlungProps, EmpfehlungState> {

    constructor(props: EmpfehlungProps) {
        super(props)
        this.state = {
            meals: []
        };
    }

    componentDidMount() {
        axios.get("/api/recommend")
            .then(value => {
                this.setState({
                    meals: value.data
                });
            })
    }

    render() {
        if (!this.props.user.loggedIn) return (<Navigate to={"/login"}/>)
        else return (
            <div>
                <h1>Tagesaktuelle Empfehlung der Mensa</h1>
                {this.state.meals != null && this.state.meals.length === 0 && <p>Mit diesen Filtern kann man dir auch nicht mehr helfen! :-(</p>}
                <ul className="list-group">
                    {this.state.meals.map((item, index) => (
                        <Meal key={index} item={item}/>
                    ))}
                </ul>
            </div>
        )
    }
}