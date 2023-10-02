import { useUserState } from "../context/UserContext";

const Home = () => {
  const { user } = useUserState();
  return (
    <div>
      <h1>Home</h1>
    </div>
  );
};

export default Home;
