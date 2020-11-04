import { CircularProgress, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead } from '@material-ui/core';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

const useStyles = makeStyles((theme) => ({    
    container: {
        maxHeight: 440,
    },
   }));


function ReviseSalary(props) {

  const [datas, setDatas ] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const classes = useStyles();
  
  useEffect(() => {
    let unmounted = false;
    const fetchDatas = async () => {      
      try {           
        setDatas(null);
        setLoading(true);        
        const res = await axios.get('http://localhost:8080/api/employee/' + props.empNo + '/salary')
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
        <Paper>
        <TableContainer className={classes.container}>     
        <Table stickyHeader>
            <TableHead>
                <TableCell align="center">연봉</TableCell>
                <TableCell align="center">시작일</TableCell>
                <TableCell align="center">종료일</TableCell>
            </TableHead>
                {datas.content.map((text) => (
                    <TableBody>
                    <TableCell align="center">${text.salary}</TableCell>
                    <TableCell align="center">{text.fromDate}</TableCell>
                    <TableCell align="center">{text.toDate === '9999-01-01' ? '현재' : text.toDate}</TableCell>
                    </TableBody>
                ))}
        </Table>
        </TableContainer>   
        </Paper>
    </div>
  )
}

export default ReviseSalary;