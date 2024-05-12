import React from "react";
import {Meal} from "./Meal";
import axios from "axios";

export type MenuItem = {
    category: string;
    criteria: string[];
    day: string;
    name: string;
    price: {
        INTERN: string;
        STUD: string;
        EXTERN: string;
    };
};

type State = {
    items: MenuItem[];
};

class Tagesplan extends React.Component<{}, State> {
    state: State = {
        items: [],
    };

    componentDidMount() {
        axios.get('/menu-plan')
            .then((response) => this.setState({items: response.data}))
    }

    getTodaysDate() {
        return new Date().toLocaleDateString("de-DE", { weekday: "long" });
    }

    render() {
        return (
            <div className="box">
                <h1>MENSA BERLINER TOR - TAGESPLAN</h1>
                <h2>{this.getTodaysDate()}</h2>
                {this.state.items != null && this.state.items.length === 0 && <p>No items found</p>}
                <ul className="list-group">
                    {this.state.items.map((item, index) => (
                        <Meal key={index} item={item}/>
                    ))}
                </ul>
            </div>
        );
    }
}

export default Tagesplan;
