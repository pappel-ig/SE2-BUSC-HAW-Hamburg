import React from "react";

type MenuItem = {
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
    const apiUrl = "http://localhost:8080/menu-plan";
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          items: data,
        });
      });
  }

  render() {
    const currentDate = new Date();
    const dayOfWeek = currentDate.toLocaleDateString("en-US", {
      weekday: "long",
    });
    return (
      <>
        <h1>MENSA BERLINER TOR - TAGESPLAN</h1>
        <h2>{dayOfWeek}</h2>
        {this.state.items && this.state.items.length === 0 && <p>No items found</p>}
        <ul className="list-group">
          {this.state.items.map((item, index) => (
            <li key={index} style={{ display: "block" }}>
              {item.name}
              <span style={{ textTransform: "lowercase", display: "block" }}>
                ({item.criteria.join(", ")})
              </span>
              <span style={{ display: "block", textAlign: "right" }}>
                {item.price.STUD}{" "}
                {item.criteria.includes("Vegan") && (
                  <img
                    src="vegan.png"
                    alt="Vegan"
                    style={{
                      float: "right",
                      marginLeft: "10px",
                    }}
                  />
                )}
                {item.criteria.includes("Vegetarisch") && (
                  <img
                    src="vegetarisch.png"
                    alt="Vegan"
                    style={{
                      float: "right",
                      marginLeft: "10px",
                    }}
                  />
                )}
                {item.criteria.includes(
                  "enthält keine laktosehaltigen Lebensmittel"
                ) && (
                  <img
                    src="laktosefrei.png"
                    alt="Vegan"
                    style={{
                      float: "right",
                      marginLeft: "10px",
                    }}
                  />
                )}
              </span>
            </li>
          ))}
        </ul>
      </>
    );
  }
}
export default Tagesplan;
