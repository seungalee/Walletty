import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createContext, React } from "react";
import { UserProvider } from "./context/UserContext";

import Login from "./pages/Login";
import Home from "./pages/Home";
import Join from "./pages/Join";
import Survey from "./pages/Survey";
import Feedback from "./pages/Feedback";
import Mission from "./pages/Mission";
import Survey_goalmoney from "./pages/Survey_goalmoney";
import Vault from "./pages/Vault";

function App() {
  return (
    <UserProvider>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/join" element={<Join />} />
            <Route path="/survey" element={<Survey />} />
            <Route path="/survey-goalmoney" element={<Survey_goalmoney />} />
            <Route path="/feedback" element={<Feedback />} />
            <Route path="/mission" element={<Mission />} />
            <Route path="/vault" element={<Vault />} />
          </Routes>
        </div>
      </BrowserRouter>
    </UserProvider>
  );
}

export default App;
