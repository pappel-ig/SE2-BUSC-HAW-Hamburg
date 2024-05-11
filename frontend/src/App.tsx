import Tagesplan from "./components/Tagesplan";
import headerImage from "./assets/mensamatch_logo.png";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="header">
        <img src={headerImage} alt="Logo" />
      </div>
      <Routes>
        <Route
          path="/"
          element={
            <>
              <div className="box">
                {/*<Link to="/Larissa">Test</Link>*/}
                <Tagesplan></Tagesplan>
              </div>
            </>
          }
        />
        <Route path="/Larissa" element={<h1>sdsda</h1>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
