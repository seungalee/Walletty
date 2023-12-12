import { PieChart, Pie, Sector, Cell } from "recharts";

const StatisticsGraph = ({ dataval }) => {
  console.log(dataval);

  const data = [
    { name: dataval[0].name, value: dataval[0].rate },
    { name: dataval[1].name, value: dataval[1].rate },
    { name: dataval[2].name, value: dataval[2].rate },
    { name: dataval[3].name, value: dataval[3].rate },
    { name: dataval[4].name, value: dataval[4].rate },
    { name: dataval[5].name, value: dataval[5].rate },
    { name: dataval[6].name, value: dataval[6].rate },
  ];
  const COLORS = [
    "#ccdcff",
    "#b3beff",
    "#9a99f2",
    "#8b79d9",
    "#805ebf",
    "#60308c",
    "#511f73",
  ];

  let renderLabel = function (entry) {
    return `${entry.name} : ${entry.value}%`;
  };

  return (
    <PieChart width={600} height={400}>
      <Pie
        data={data}
        cx={300}
        cy={200}
        innerRadius={100}
        outerRadius={150}
        fill="#8884d8"
        paddingAngle={2}
        dataKey="value"
        label={renderLabel}
        isAnimationActive={false}
      >
        {data.map((entry, index) => (
          <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
        ))}
      </Pie>
    </PieChart>
  );
};

export default StatisticsGraph;
