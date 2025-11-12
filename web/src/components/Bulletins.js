import { useLayoutEffect, useState } from 'react';


function Bulletins() {
  const [bulletins, setBulletins] = useState([]);

  useLayoutEffect(() => {
    const getBulletins = async() => {
      const res = await fetch('../../../api/v1/bulletins');
      const bulletins = await res.json();
      setBulletins(bulletins);
    }

    getBulletins().catch(e => {
      console.log('Error fetching bulletins: ', e);
    })

  })
  return (
    <div>
    {bulletins.map(bulletin => (
      <div>
        <h2>{bulletin.title}</h2>
        <p>{bulletin.content}</p>
      </div>
    ))}
     </div>
  );
 
}

export default Bulletins;