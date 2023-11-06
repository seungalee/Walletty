const Toss = () => {
  const tossApiPayment = () => {
    return {
      __html:
        '<iframe src="./Toss.html" width="800px" height="600px"></iframe>',
    };
  };
  return (
    <div>
      <div dangerouslySetInnerHTML={tossApiPayment()} />
    </div>
  );
};

export default Toss;
