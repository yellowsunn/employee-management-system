import { CircularProgress } from '@material-ui/core';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

// const useStyles = makeStyles((theme) => ({
//  root: {
//    backgroundColor : theme.palette.background.default,
//  },
//  paper: {
//   padding : theme.spacing(1),
//   marginBottom : theme.spacing(1),
//  },
//  retire: {
//   padding : theme.spacing(1),
//   marginTop : theme.spacing(1),
//   marginBottom : theme.spacing(1),
//  },
// }));


function Delete(props) {

  const [datas, setDatas ] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  //const classes = useStyles();
  
  useEffect(() => {
    let unmounted = false;
    const fetchDatas = async () => {      
      try {           
        setDatas(null);
        setLoading(true);        
        const res = await axios.delete('http://localhost:8080/api/employee/delete/' + props.empNo )
        if(!unmounted)
          setDatas(res.data);     
      } catch (e) {        
        setError(e);
      }
      if(!unmounted) setLoading(false);            
    };
    fetchDatas();  
    return () => {//clean up      
      unmounted = true;
    }  
  },[props.empNo])
  if(loading) {
    return(
    <div >
      <CircularProgress color="secondary" />        
    </div>
    )
  }
  if(error) {
    return <div>error</div>
  }
  if (!datas) return null;

  return (
    <div>
        {datas.success && 
        <div>
            삭제 성공
        </div>
        }
        {datas.success === false && 
        <div>
            삭제 실패
        </div>
        }
        임시 메시지
        {console.log( props.empNo)}
    </div>
  )
}

export default Delete;