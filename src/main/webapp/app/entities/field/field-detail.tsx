import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './field.reducer';
import { IField } from 'app/shared/model/field.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFieldDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FieldDetail = (props: IFieldDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { fieldEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Field [<b>{fieldEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fieldName">Field Name</span>
          </dt>
          <dd>{fieldEntity.fieldName}</dd>
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{fieldEntity.peckOrder}</dd>
          <dt>Data Source</dt>
          <dd>{fieldEntity.dataSourceParamTitle ? fieldEntity.dataSourceParamTitle : ''}</dd>
          <dt>Data Type</dt>
          <dd>{fieldEntity.dataTypeParamTitle ? fieldEntity.dataTypeParamTitle : ''}</dd>
          <dt>Cascader</dt>
          <dd>{fieldEntity.cascaderCascaderName ? fieldEntity.cascaderCascaderName : ''}</dd>
        </dl>
        <Button tag={Link} to="/field" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/field/${fieldEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ field }: IRootState) => ({
  fieldEntity: field.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FieldDetail);
