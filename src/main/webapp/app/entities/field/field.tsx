import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './field.reducer';
import { IField } from 'app/shared/model/field.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFieldProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Field = (props: IFieldProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { fieldList, match, loading } = props;
  return (
    <div>
      <h2 id="field-heading">
        Fields
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Field
        </Link>
      </h2>
      <div className="table-responsive">
        {fieldList && fieldList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Field Name</th>
                <th>Peck Order</th>
                <th>Data Source</th>
                <th>Data Type</th>
                <th>Cascader</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fieldList.map((field, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${field.id}`} color="link" size="sm">
                      {field.id}
                    </Button>
                  </td>
                  <td>{field.fieldName}</td>
                  <td>{field.peckOrder}</td>
                  <td>
                    {field.dataSourceParamTitle ? (
                      <Link to={`cascader-param/${field.dataSourceId}`}>{field.dataSourceParamTitle}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {field.dataTypeParamTitle ? <Link to={`cascader-param/${field.dataTypeId}`}>{field.dataTypeParamTitle}</Link> : ''}
                  </td>
                  <td>{field.cascaderCascaderName ? <Link to={`cascader/${field.cascaderId}`}>{field.cascaderCascaderName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${field.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${field.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${field.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Fields found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ field }: IRootState) => ({
  fieldList: field.entities,
  loading: field.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Field);
